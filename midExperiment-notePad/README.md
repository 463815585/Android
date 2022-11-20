# 期中实验notePad

## 1、增加时间戳功能：

- 实现思路——

  首先必须需要在ListView列表的item中添加显示时间戳的TextView组件来进行显示，其二在NotesListt.class中添加修改后的时间的标记，dataColumns数组和viewIDs数组中添加对应的标记名和item中对应显示时间戳的组件ID，这样即可看到以毫秒显示出来一大串时间。

  如图：

  ![](C:\Users\46381\Desktop\midExperiment-notePad\img\毫秒时间戳.jpg)

  代码：

  noteslist_item.xml在美化功能中查看[美化功能](#3、美化主页面列表显示：)

  NotesList.java

  ```java
  /**
       * The columns needed by the cursor adapter
       */
      private static final String[] PROJECTION = new String[] {
              NotePad.Notes._ID, // 0
              NotePad.Notes.COLUMN_NAME_TITLE, // 1
              NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,  // 2 createTime or updateTime
              NotePad.Notes.COLUMN_NAME_NOTE  // 3 笔记内容
      };
  ```

  ```java
  // The names of the cursor columns to display in the view, initialized to the title column
          String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE
                  ,NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE
                  ,NotePad.Notes.COLUMN_NAME_NOTE } ;
  
  // The view IDs that will display the cursor columns, initialized to the TextView in
  // noteslist_item.xml
  int[] viewIDs = { R.id.text1,R.id.time_view,R.id.summary };
  ```

  期中笔记的部分内容也添加到首页中美化了页面。

  由于时间戳的显示并不是我们日常所看到的时间，所以需要对时间进行格式化后再进行显示，实现代码如下：

  ```java
  // 自定义时间显示格式
  SimpleCursorAdapter.ViewBinder viewBinder = new SimpleCursorAdapter.ViewBinder() {
      @Override
      public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
          if(cursor.getColumnIndex(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE) == columnIndex){
              TextView textView = (TextView) view;
              SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd\nHH:mm:ss", Locale.CHINA);
              Date date = new Date(cursor.getLong(columnIndex));
              String time = format.format(date);
              textView.setText(time);
              return true;
          }
          return false;
      }
  };
  adapter.setViewBinder(viewBinder);   // 格式化时间格式
  ```

  实现后如图：

  ![](C:\Users\46381\Desktop\midExperiment-notePad\img\格式化时间戳.jpg)

## 2、增加笔记查询功能

- 实现思路——

  首先在菜单布局list_options_menu.xml中添加搜索按钮的菜单选项。代码如下：

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <menu xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto">
      <!--  This is our one standard application action (creating a new note). -->
      <item
          android:id="@+id/search"
          android:icon="@drawable/search"
          android:title="Search"
          android:actionViewClass="android.widget.SearchView"
          android:showAsAction="always" />
      <item android:id="@+id/menu_add"
            android:icon="@drawable/ic_menu_compose"
            android:title="@string/menu_add"
            android:alphabeticShortcut='a'
            android:showAsAction="always" />
      <!--  If there is currently data in the clipboard, this adds a PASTE menu item to the menu
            so that the user can paste in the data.. -->
      <item android:id="@+id/menu_paste"
            android:icon="@drawable/ic_menu_compose"
            android:title="@string/menu_paste"
            android:alphabeticShortcut='p' />
  </menu>
  ```

  之后在NotesList.java中实现搜索功能的代码，代码原理在nCreateOptionsMenu(Menu menu)方法添加，首先利用类SearchView来装饰搜索按钮组件，即可实现点击时弹出搜索条，再利用setOnQueryTextListener(OnQueryTextListener listener)方法实现搜索输入内容的监听，在onQueryTextChange(String s)方法中实现监听怎么样的数据处理，最后和原先笔记本首页在ListView中显示item的代码如此显示到页面中。代码如下：

  ```java
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate menu from XML resource
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.list_options_menu, menu);
  
      // Generate any additional actions that can be performed on the
      // overall list.  In a normal install, there are no additional
      // actions found here, but this allows other applications to extend
      // our menu with their own actions.
      Intent intent = new Intent(null, getIntent().getData());
      intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
      menu.addIntentOptions(Menu.CATEGORY_ALTERNATIVE, 0, 0,
                            new ComponentName(this, NotesList.class), null, intent, 0, null);
  
      //搜索功能的实现
      MenuItem mSearch = menu.findItem(R.id.search);
      SearchView mSearchView = (SearchView)mSearch.getActionView();
      mSearchView.setQueryHint("搜索");
      mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String s) {
              return false;
          }
          @Override
          public boolean onQueryTextChange(String s) {
              Cursor cursor = managedQuery(
                  getIntent().getData(),            // Use the default content URI for the provider.
                  PROJECTION,                       // Return the note ID and title for each note.
                  NotePad.Notes.COLUMN_NAME_TITLE+" like ? or "+NotePad.Notes.COLUMN_NAME_NOTE+" like ?", // No where clause, return all records.
                  new String[]{"%"+s+"%","%"+s+"%"}, // No where clause, therefore no where column values.
                  NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.
              );
              String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE,
                                      NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,
                                      NotePad.Notes.COLUMN_NAME_NOTE} ;
              int[] viewIDs = { R.id.text1, R.id.time_view, R.id.summary };//加入修改时间
              SimpleCursorAdapter adapter
                  = new SimpleCursorAdapter(
                  NotesList.this,                             // The Context for the ListView
                  R.layout.noteslist_item,          // Points to the XML for a list item
                  cursor,                           // The cursor to get items from
                  dataColumns,
                  viewIDs
              );
              // 自定义时间显示格式
              SimpleCursorAdapter.ViewBinder viewBinder = new SimpleCursorAdapter.ViewBinder() {
                  @Override
                  public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                      if(cursor.getColumnIndex(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE) == columnIndex){
                          TextView textView = (TextView) view;
                          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd\nHH:mm:ss", Locale.CHINA);
                          Date date = new Date(cursor.getLong(columnIndex));
                          String time = format.format(date);
                          textView.setText(time);
                          return true;
                      }
                      return false;
                  }
              };
              adapter.setViewBinder(viewBinder);   // 格式化时间格式
  
              // Sets the ListView's adapter to be the cursor adapter that was just created.
              setListAdapter(adapter);
              return false;
          }
      });
  
      return super.onCreateOptionsMenu(menu);
  }
  ```

  实现截图：

  ![](C:\Users\46381\Desktop\midExperiment-notePad\img\搜索功能.jpg)

## 3、美化主页面列表显示：

1. 实现思路：

   在noteslist_item.xml中重新排版，用线性布局，添加了笔记图标、时间戳、笔记标题和笔记内容的显示。在线性布局上添加笔记图标和一个嵌套纵向线性布局，在纵向布局中再添加一个横向线性布局和一个TextView，其中TextView用来显示部分文章内容，横向线性布局用来添加两个TextView，其中一个TextView用来显示标题，一个用来显示时间戳，在TextView中有一个属性可用来将超出TextView宽度文字以三点水省略号显示，已达到更好的美化效果，其余属性具体代码如下：

   noteslist_item.xml

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
       android:orientation="horizontal"
       android:layout_width="match_parent"
       android:layout_height="match_parent">
   
       <LinearLayout
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:orientation="horizontal">
           <ImageView
               android:layout_width="50dp"
               android:layout_height="50dp"
               android:padding="10dp"
               android:src="@drawable/note"
               android:layout_gravity="center_vertical"/>
           <LinearLayout
               android:layout_width="0dp"
               android:layout_height="wrap_content"
               android:layout_weight="1"
               android:orientation="vertical">
               <LinearLayout
                   android:layout_width="match_parent"
                   android:layout_height="wrap_content"
                   android:orientation="horizontal">
                   <TextView
                       android:layout_width="200dp"
                       android:layout_height="wrap_content"
                       android:layout_gravity="center"
                       android:id="@+id/text1"
                       android:text="你好！！"
                       android:paddingLeft="12dp"
                       android:singleLine="true"
                       android:textAppearance="?android:attr/textAppearanceLarge"
                       android:textStyle="bold"/>
                   <TextView
                       android:id="@+id/time_view"
                       android:layout_width="0dp"
                       android:layout_height="wrap_content"
                       android:layout_weight="1"
                       android:gravity="right"
                       android:paddingRight="12sp"
                       android:text="2000-10-19\n10:50:30"
                       android:layout_gravity="center"/>
               </LinearLayout>
               <TextView
                   android:id="@+id/summary"
                   android:layout_width="match_parent"
                   android:layout_height="25dp"
                   android:gravity="center_vertical"
                   android:paddingLeft="12dp"
                   android:paddingRight="12dp"
                   android:text="nihaoahaafaadfadsaadfadafaadadadaadfadfafafadfadafafafa"
                   android:singleLine="true"/>
           </LinearLayout>
       </LinearLayout>
   </LinearLayout>
   ```

## 4、完善笔记本功能

1. 原因：因为在原先的notepad中，新创建一个笔记是没有标题和正文区分的，新创建的笔记输入内容全部用来做为标题和正文共用，只有第二次进入刚刚创建的笔记中才可设置标题。

2. 修改：使其在首次进入便可创建标题和正文，而不是全部内容都都作为标题和正文共用，如图所示：![](C:\Users\46381\Desktop\midExperiment-notePad\img\noteInsert.jpg)

   ![](C:\Users\46381\Desktop\midExperiment-notePad\img\noteList.jpg)

   ![](C:\Users\46381\Desktop\midExperiment-notePad\img\noteEditor.jpg)

3. 实现思路：首先修改原先的note_editor.xml布局，代码如下：

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
       android:background="@android:color/transparent"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:orientation="vertical"
       android:paddingLeft="6dip"
       android:paddingRight="6dip"
       android:paddingBottom="3dip">
   
       <EditText
           android:id="@+id/noteTitle"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:hint="标题"
           android:textSize="28sp"
           android:maxLines="1"
           android:layout_marginTop="2dp"
           android:ems="25"
           android:scrollHorizontally="true"
           />
       <EditText
           android:id="@+id/note"
           android:layout_marginTop="5dp"
           android:layout_width="match_parent"
           android:layout_height="match_parent"
           android:scrollbars="vertical"
           android:fadingEdge="vertical"
           android:gravity="top"
           android:textSize="22sp"
           android:capitalize="sentences"
           android:background="@null"
           android:hint="正文"
       />
   
   </LinearLayout>
   ```

4. 修改NoteEditor.java中代码，获取标题组件EditText，

   - 在onOptionsItemSelected(MenuItem item)方法和onPause()方法中当点击ID等于menu_save组件时或点击退出按钮销毁noteEditor页面时添加标题内容传到updateNote(String text, String title)方法来存储到文档中。

   - 在onResume()方法中添加当点击首页列表中已创建的笔记将对应的标题和笔记内容传到编辑页面中，代码如下：

     onOptionsItemSelected(MenuItem item)

     ```java
     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         // Handle all of the possible menu actions.
         switch (item.getItemId()) {
             case R.id.menu_save:
                 String text = mText.getText().toString();
                 String title = mTitle.getText().toString();
                 updateNote(text, title);
                 finish();
                 break;
             case R.id.menu_delete:
                 deleteNote();
                 finish();
                 break;
             case R.id.menu_revert:
                 cancelNote();
                 break;
         }
         return super.onOptionsItemSelected(item);
     }
     ```

     onPause()

     ```java
     @Override
     protected void onPause() {
         super.onPause();
     
         /*
              * Tests to see that the query operation didn't fail (see onCreate()). The Cursor object
              * will exist, even if no records were returned, unless the query failed because of some
              * exception or error.
              *
              */
         if (mCursor != null) {
     
             // Get the current note text.
             String text = mText.getText().toString();
             String title = mTitle.getText().toString();
             int length = text.length();
     
             /*
                  * If the Activity is in the midst of finishing and there is no text in the current
                  * note, returns a result of CANCELED to the caller, and deletes the note. This is done
                  * even if the note was being edited, the assumption being that the user wanted to
                  * "clear out" (delete) the note.
                  */
             if (isFinishing() && (length == 0)) {
                 setResult(RESULT_CANCELED);
                 deleteNote();
     
                 /*
                      * Writes the edits to the provider. The note has been edited if an existing note was
                      * retrieved into the editor *or* if a new note was inserted. In the latter case,
                      * onCreate() inserted a new empty note into the provider, and it is this new note
                      * that is being edited.
                      */
             } else if (mState == STATE_EDIT) {
                 // Creates a map to contain the new values for the columns
                 updateNote(text, title);
             } else if (mState == STATE_INSERT) {
                 updateNote(text, title);
                 mState = STATE_EDIT;
             }
         }
     }
     ```

     onResume()

     ```java
     @Override
     protected void onResume() {
         super.onResume();
     
         /*
              * mCursor is initialized, since onCreate() always precedes onResume for any running
              * process. This tests that it's not null, since it should always contain data.
              */
         if (mCursor != null) {
             // Requery in case something changed while paused (such as the title)
             mCursor.requery();
     
             /* Moves to the first record. Always call moveToFirst() before accessing data in
                  * a Cursor for the first time. The semantics of using a Cursor are that when it is
                  * created, its internal index is pointing to a "place" immediately before the first
                  * record.
                  */
             mCursor.moveToFirst();
     
             // Modifies the window title for the Activity according to the current Activity state.
             if (mState == STATE_EDIT) {
                 // Set the title of the Activity to include the note title
                 int colTitleIndex = mCursor.getColumnIndex(NotePad.Notes.COLUMN_NAME_TITLE);
                 String title = mCursor.getString(colTitleIndex);
                 Resources res = getResources();
                 String text = String.format(res.getString(R.string.title_edit), title);
                 setTitle(text);
                 // Sets the title to "create" for inserts
             } else if (mState == STATE_INSERT) {
                 setTitle(getText(R.string.title_create));
             }
     
             /*
                  * onResume() may have been called after the Activity lost focus (was paused).
                  * The user was either editing or creating a note when the Activity paused.
                  * The Activity should re-display the text that had been retrieved previously, but
                  * it should not move the cursor. This helps the user to continue editing or entering.
                  */
     
             // Gets the note text from the Cursor and puts it in the TextView, but doesn't change
             // the text cursor's position.
             int colNoteIndex = mCursor.getColumnIndex(NotePad.Notes.COLUMN_NAME_NOTE);
             String note = mCursor.getString(colNoteIndex);
             mText.setTextKeepState(note);
             int colTitleIndex = mCursor.getColumnIndex(NotePad.Notes.COLUMN_NAME_TITLE);
             String title = mCursor.getString(colTitleIndex);
             mTitle.setTextKeepState(title);
     
             // Stores the original note text, to allow the user to revert changes.
             if (mOriginalContent == null) {
                 mOriginalContent = note;
             }
     
             /*
              * Something is wrong. The Cursor should always contain data. Report an error in the
              * note.
              */
         } else {
             setTitle(getText(R.string.error_title));
             mText.setText(getText(R.string.error_message));
         }
     }
     ```

     由于编辑页面添加了标题，那么原先添加标题的的功能便可去掉，使其不显得多余，并且自认为Revert changes选项没有必要必须监听到笔记内容修改才出现，一直出现会更好，不至于让用户感觉到莫名其妙出现的功能，个人在应用时就是到后面才发现了这个菜单选项。实现这些功能需要注释掉onCreateOptionsMenu(Menu menu)创建菜单方法和onPrepareOptionsMenu(Menu menu)菜单动态选项方法中的部分代码，如下：

     onCreateOptionsMenu(Menu menu)

     ```java
     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate menu from XML resource
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.editor_options_menu, menu);
     
         // Only add extra menu items for a saved note 
         if (mState == STATE_EDIT) {
             // Append to the
             // menu items for any other activities that can do stuff with it
             // as well.  This does a query on the system for any activities that
             // implement the ALTERNATIVE_ACTION for our data, adding a menu item
             // for each one that is found.
             Intent intent = new Intent(null, mUri);
             intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
             //            menu.addIntentOptions(Menu.CATEGORY_ALTERNATIVE, 0, 0,
             //                    new ComponentName(this, NoteEditor.class), null, intent, 0, null);
         }
     
         return super.onCreateOptionsMenu(menu);
     }
     ```

     onPrepareOptionsMenu(Menu menu)

     ```java
     @Override
     public boolean onPrepareOptionsMenu(Menu menu) {
         // Check if note has changed and enable/disable the revert option
         // 恢复原先内容操作菜单选项使其一直出现
         //        int colNoteIndex = mCursor.getColumnIndex(NotePad.Notes.COLUMN_NAME_NOTE);
         //        String savedNote = mCursor.getString(colNoteIndex);
         //        String currentNote = mText.getText().toString();
         //        if (savedNote.equals(currentNote)) {
         //            menu.findItem(R.id.menu_revert).setVisible(false);
         //        } else {
         //            menu.findItem(R.id.menu_revert).setVisible(true);
         //        }
         return super.onPrepareOptionsMenu(menu);
     }
     ```

     