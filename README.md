# Web_Quiz_Engine_API
Multi-user web service for creating and solving quizzes using REST API, an embedded database, security, and other technologies.

In the Internet, you can often find sites where you need to answer questions: educational sites, sites with psychological tests, 
job search services, or just entertaining sites like web quests. Something they all have in common is that they permit to answer 
questions (or quizzes) and then see the results.

To create a new quiz, the client needs to send a JSON as the request's body via POST to /api/quizzes. The JSON should contain 
the four fields: title (a string), text (a string), options (an array of strings) and answer (integer index of the correct option). 

To get a quiz by id, the client sends the GET request to /api/quizzes/{id}.

To get all existing quizzes in the service, the client sends the GET request to /api/quizzes.

To solve the quiz, the client sends a POST request to /api/quizzes/{id}/solve and passes the answer parameter in the content. 
This parameter is the index of a chosen option from options array. As before, it starts from zero.

The service returns a JSON with two fields: success (true or false) and feedback (just a string). 
