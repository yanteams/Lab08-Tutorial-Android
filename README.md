# Tutorial How to learn Data Storage – SQLite Database – Lab08
Target:
- Understand basic Data Storage, Shared preferences.
References:
https://vncoder.vn/bai-viet/android-tim-hieu-ve-internal-storage-external-storage-va-scoped-storage-trong-android
https://viblo.asia/p/su-dung-sqlite-database-trong-ung-dung-android-wjAM7alevmWe
Data storage:
Storage options in Android:
+ Shared Preferences: Save basic data in key-value pairs (color, option, …).
+ Internal Storage: Store private data on device memory.
+ External Storage: Store public data on shared external storage.
+ Sqlite Database: Store structured data in a private database.
+ Network Connection: Store data on the web with a network server.
MODE - File open mode (open the file for what, grant permission):
MODE_PRIVATE: Create or replace an existing file with the same name and use this file only for
the program.
MODE_APPEND: Create a new file to write to or append to if the file exists and has data.
MODE_WORLD_READABLE: Allows other programs to read data from the file.
MODE_WORLD_WRITEABLE: Allows other programs to write data to the file.
Firstly, create a project and click Choose Empty Views Activity. After that give the name Project “Lab08” Choose language Java and click Finish.
![image](https://github.com/yanteams/Lab08-Tutorial-Android/assets/22448178/cf907c75-8c36-4bd0-bc27-6145a81dd38a)
![image](https://github.com/yanteams/Lab08-Tutorial-Android/assets/22448178/18501d78-b8df-4021-8f0e-a5e2cfa1152a)
Enter activity_main.xml on the toolbar/title after that click Code right side.
Next is update code:

