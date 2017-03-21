# Social network back end as RESTful web services

## Warning:
Storages are stubbed as this is a demo project. No authentication added so all information is public. Do not store private information or attempt to use as a live website backend using out of the box code.

***

## Profiles
Profiles are entities to represent people in the social network.
Each profile has a unique 'profileName' and can be individually accessed by using this 'profileName'
APIs support GET, POST, PUT and DELETE. Requests and Responses are JSON only at the moment.

### JSON Syntax
```javascript
{
  "created":"2017-03-21T20:31:38.395Z",
  "firstName":"Aditya",
  "id":1,
  "lastName":"Advani",
  "profileName":"Aditya101"
}
```

### GET
Used for fetching profile details. Requests can be made to two endpoints
1. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/profiles
    * this fetches details for all available profiles
2. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/profiles/{profileName}
    * this fetches details for the specified profile
    
### POST
Used for adding a new profile. Request can be made to one endpoint only.
1. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/profiles
    * make sure to set Content-Type : application/json in the Headers portion of the request
    * minimum requirements to add a new profile:
```javascript
{
  "firstName":"Aditya",
  "lastName":"Advani",
  "profileName":"Aditya101"
}
```
    
### PUT
Used for updating an existing profile. Request can be made to one endpoint only.
1. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/profiles/{profileName}
    * make sure to set Content-Type : application/json in the Headers portion of the request
    * No minimum requirements for the request body
    * changing profileName will modify the 'author' property for all messages and comments authored by this user
    
### DELETE
Used for deleting an existing profile. Request can be made to one endpoint only.
1. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/profiles/{profileName}
    * No body required.
    * This will permanently delete the profile.
    * All messages and comments authored by this profile will be deleted
   
***   
   
## Messages
Messages are posts made by a profile in the social network. 
A message starts a new conversation thread that can be interacted with using comments
Each Message has a unique 'id' and can be individually accessed by using this 'id'
APIs support GET, POST, PUT and DELETE. Requests and Responses are JSON only at the moment.
A message cannot be authored if the authors profile doesn't exist.

### JSON Syntax
```javascript
{
  "author": "Aditya101",
  "created": "2017-03-21T20:52:50.44Z",
  "id": 1,
  "message": "Hello World"
}
```

### GET
Used for fetching message details. Requests can be made to two endpoints
1. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/messages
    * this fetches details for all available messages
2. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/messages/{message-ID}
    * this fetches details for the specified message

### POST
Used for adding a new messages. Request can be made to one endpoint only.
1. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/messages
    * make sure to set Content-Type : application/json in the Headers portion of the request
    * minimum requirements to add a new message:
```javascript
{
  "message":"Your new message",
  "author":"Valid-Author-Name"
}
```

### PUT
Used for updating an existing message. Request can be made to one endpoint only.
1. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/messages/{message-ID}
    * make sure to set Content-Type : application/json in the Headers portion of the request
    * No minimum requirements for the request body
    * Can change only the message contents
    
### DELETE
Used for deleting an existing message. Request can be made to one endpoint only.
1. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/message/{message-ID}
    * No body required.
    * This will permanently delete the message
    * All comments on the message will be deleted
   
***   
   
## Comments
Comments are a sub-resource and are a way to converse on a posted message in the social network. 
Each Comment has a unique 'id' and can be individually accessed by using this 'id' and its unique 'message-ID'
APIs support GET, POST, PUT and DELETE. Requests and Responses are JSON only at the moment.
A comment cannot be authored if the authors profile doesn't exist.

### JSON Syntax
```javascript
{
  "author": "Aditya101",
  "created": "2017-03-21T21:04:21.193Z",
  "id": 1,
  "message": "Hello"
}
```

### GET
Used for fetching comment details. Requests can be made to two endpoints
1. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/messages/{message-ID}/comments
    * this fetches details for all available comments for the message with id = message-ID
2. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/messages/{message-ID}/comments/{comment-ID}
    * this fetches details for the specified comment from the message with id = message-ID

### POST
Used for adding a new comment. Request can be made to one endpoint only.
1. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/messages/{message-ID}/comments
    * Since comments is a sub-resource, a valid message needs to exist before a comment can be added
    * make sure to set Content-Type : application/json in the Headers portion of the request
    * minimum requirements to add a new message:
```javascript
{
  "message":"Your new comment",
  "author":"Valid-Author-Name"
}
```

### PUT
Used for updating an existing comment. Request can be made to one endpoint only.
1. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/messages/{message-ID}/comments/{comment-ID}
    * make sure to set Content-Type : application/json in the Headers portion of the request
    * No minimum requirements for the request body
    * Can change only the comment contents
    
### DELETE
Used for deleting an existing message. Request can be made to one endpoint only.
1. http://sample-env.prenpt36dm.us-west-2.elasticbeanstalk.com/backend/message/{message-ID}/comments/{comment-ID}
    * No body required.
    * This will permanently delete the comment
  
***  
  
## Variables
Variales represented in { } on the links should be enterd without the curly braces.
for example, /messages/{2} should be entered as /messages/2

## In progress
Adding Error handling and HATEOAS
