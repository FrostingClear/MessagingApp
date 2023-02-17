Introduction:

This was a class project to build a messaging application, both the server-side and client-side applications. Demonstrating the use of socket programming and javafx services and threads.

The application has basic features like real-time chat messaging (with messages being stored on a sql database) and online indicators.

It is designed to operate in conjunction with a sql database. Messages are AES encrypted (in the current config, user login data is not) The server application can be deployed on the cloud if desired with minor adjustments (see how to run)

How to run:

This is a JavaFX application, update your build path and run configurations accordingly.

The server side application should be started by running "ServerUI.java" The client side application should be started by running "ClientUI.java"

In this demo state, the AES encryption key is a key generated by "OneOffKeygen.java" which both the client and server share.

If deploying the server side application on a remote VM as a runnable .jar file, then update the aes key filepath accordingly.

I've included a demo database (frostclearMsgDatabase.sql) with some users on it and the appropriate table structures for use. 
- In Server.java on line 105 and 106, set your localhost username and password accordingly