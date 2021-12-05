# Document Revision History
Rev 1.0 2020-10-27 - initial version
- 1.0 : initial version
- 1.1 : Add bird's eye view
- 1.2 : Update docs hierarchy
- 1.3 : Update model, view details
- 1.4 : Revise about chatroom discord

# System Architecture
## Bird's eye view
![birds-eye-3](https://user-images.githubusercontent.com/28827932/97774880-dce96c80-1b9e-11eb-88eb-97edcd0e73d5.png)
---

## MVC pattern
![VMC](https://user-images.githubusercontent.com/26455280/97210932-a52a9f80-1801-11eb-8778-d3d7bc1c63d2.PNG)

We have 13 views and 5 models.

### Model
Here is E-R (Entity Relationship) diagram for model design.

![er diagram](https://user-images.githubusercontent.com/26455280/97217391-8da3e480-180a-11eb-85be-906a4a7bc9fa.PNG)

Rectangle represents entity sets, diamond represents relationship sets, and numbers on line represent cardinality constraints (min...max). Rectangle includes attributes inside, and underlined attributes are primary key.

We would make all of users to set up tag right after sign up, but there is a moment that user doesn't have any tag. So, we set the number of minimum tag user have to 0, not 1.

---

Here is relation schema diagram on E-R diagram. It would be used by the structures of Django models. Methods in lower box are core methods of each model.

![image](https://user-images.githubusercontent.com/26455280/99102971-30899a80-2622-11eb-9514-d213cf498c0c.png)


Rectangle represents relation schema. Schema attributes are in the upper box, and core methods are in the lower box. Underlined attributes are primary key, and arrowed attributes are foreign key.

### View
The user interface for view design is shown below.
![스크린샷 2020-11-28 오전 10 23 12](https://user-images.githubusercontent.com/60940673/100490999-c580ad80-3163-11eb-83d3-4c4a6931e2e7.png)


The functionality and the requirement for each page are as follows:

#### Login Part
1. Login Page (‘/login‘)
* If the user clicks ‘Login with Discord’ button, login is handled by Discord API.
* If the user is new, get user info(‘username’ and ‘avatar’) from Discord account.


#### Posts Part
2. Posts Page (‘/post‘)
* Show posts with chosen game tags
  * If the user has no tag, “Search Your Game Tag” is shown.
* If the user sets ‘See Friend’s Posts’ toggle, show friends’ posts, and if not, show recommended posts.
* Post has ‘shallWe’, ’like’, ‘comment’ buttons.
  * If the user is in a room, ‘shallWe’ button is disabled.
  * If the user clicks ‘shallWe’ button, the author of the post receives shallWe.
  * If the user clicks ‘like’ button, the number of likes of the post increments by 1.
  * If the user clicks ‘comment’ button, shows Comment pop-up.
  * If the user clicks ‘avatar’ of the author, redirect to UserPage of the author.

3. Comment Pop-up
* Show comments of certain post.
* Get ‘comment’ as user input.
* If user clicks ‘submit’, comment is saved.


#### Lobby Part
4. Lobby Page (‘/lobby’)
* Show shallWe’s, and rooms with chosen game tags
* If the user clicks ‘Create New Room’ button, show CreateRoom pop-up.
* The list of shallWe’s are shown above the list of rooms.
  * ShallWe has ‘Sure’ and ‘Sorry’ button. 
    * ‘Sure’ button is disabled when the user is already in a room.
    * If the user clicks ‘Sure’ button, redirect to the room.
    * If the user clicks ‘Sorry’ button, the shallWe disappears.
  * Room has ‘Join’ button.
    * ‘Join’ button is disabled when the user is already in a room.
    * If the user clicks ‘Join’ button, redirect to the room.

5. Create New Room Pop-up
* Get ‘chatroom name’, ‘tag’, ‘max personnel’  as user input.
* If the user clicks ‘Create’ button, new chatroom is created and redirect to the chatroom.

6. Chatroom Page (‘/lobby/:id’)
* Members are listed in left. 
  * If the user clicks ‘follow’ button of a member, he/she is added as friend.
  * If the user clicks ‘invite’ button, show Invite pop-up.
* Members can chat in this page.
* Host of the room can set ‘global’ toggle, ‘chatroom name’ and ‘max personnel’.
  * If ‘Global’ toggle is set, the room is shown in lobby.
  * If the user clicks ‘chatroom name’, show Create New Room pop-up so that the user can edit ‘chatroom name’ and ‘max personnel’.


7. Invite Friend Pop-up
* There are 'Invite' buttons for each friend.
  * 'Invite' button is disabled if a friend is logged-out.
  * If the user clicks the buttons, invitation is sent to him/her.


#### Search Part
8. Search Page (‘/search’)
* Search games or users
* If the user clicks ‘search’ button, show list of searched games and users by getting ‘searched text’ as user input.
  * Searched game has ‘delete’ button if the user has corresponding tag, and has ‘add’ button if not.
  * Searched user has ‘unfollow’ button if he/she is a friend of the user, and has ’follow’ button if not.
  * If the user clicks ‘avatar’ of searched user, redirect to UserPage of searched user.

9. User Page (‘/user/:id’)
* Show his/her avatar with introduction and posts with chosen game tags.
* If the user clicks a post, shows ViewSelectedPost pop-up.
* If the user clicks ‘follow’ button, he/she is added to the user’s friend.
* If the user clicks ‘shallWe’ button, shallWe is sent to him/her.

10. ViewSelectedPost Pop-up in User Page
* Show details of certain post
* Function as same as post in Posts page.


#### MyPage Part
11. My Page (‘/user/:id’)
* Show my avatar, friend list, and my posts with chosen game tags.
* If the user clicks a post, shows ViewSelectedPost pop-up.
* If the user clicks ‘setting’ button, redirect to Setting page.
* If the user clicks ‘Create Post’ button, redirect to CreatePost page.

12. ViewSelectedPost Pop-up in My Page
* Show details of certain post
* Function similarly as post in Posts page, but no ‘shallWe’ button.
  * Instead, it has ‘edit’ and ‘delete’ button.
  * If the user clicks ‘edit’ button, redirect to CreatePost page.
  * If the user clicks ‘delete’ button, the post is deleted.

13. CreatePost Page (‘/user/:id/create’)
* Get game tag, image, and text as user inputs.
* If the user clicks ‘submit’ button, new post is created. 

14. Setting Page (‘/user/:id/setting’)
* Information of the user(‘avatar’, ’username’, ‘tag’s) are shown as placeholders.
* If the user clicks ‘Save’ button, update user info by getting user inputs.
* If the user unset ‘Receive shallWe’ toggle, the user does not receive shallWe’s.

### Controller
![controller](https://user-images.githubusercontent.com/60940673/97517107-f3919700-19d7-11eb-87c8-f6f81459f58c.png)

Left side is the view part(frontend) and right side is model part(backend). Left-to-right arrow describes API and JSON requests with user inputs from the view, and right-to-left arrow describes API and JSON responses with data from the model.   

# Design Details

## Frontend Design

Here is frontend containers and components.   
The attributes and the methods of each container and component are listed in each box.   
Conatiners are based on ‘Page’ unit of our service, where the components are composed of sub-components, main-components(which consists sub-components), independent components(which has no relationship with other components), and Popup Components(overlaps with other components described before).

### Frontend Components
<img width="498" alt="container" src="https://user-images.githubusercontent.com/60940673/97445700-e68d8d00-1970-11eb-998a-674c91f25416.png">

<img width="521" alt="component" src="https://user-images.githubusercontent.com/34087946/97771051-9256f880-1b7c-11eb-8df1-033992416573.png">

<img width="265" alt="organization of component" src="https://user-images.githubusercontent.com/34087946/97771053-9551e900-1b7c-11eb-9a50-49f3462de4f8.png">

### Frontend Algorithms

#### [Containers]    

1. Login   
- onClickLogInButton(): Redirect to ‘Discord’ and authenticate user

2. MyPage   
- onClickPost() : Show all the details of selected post in pop-up form. → Call backend api 
- onClickCreatePost() : Redirect to CreatePost page
- onClickTag() : Toggle the selected tag ( display posts filtered by selected tags )

3. Setting   
- onClickSave(avatar : image, username : string, tags : int) : Save changed - information about user.

4. Post   
- onTogglePostType() :  Toggle whether to watch recommended global posts or only friend’s posts

5. Search   
- onClickSearch(keyword : string) : Search and show the result of users and tags objects with the keyword in their name
- onClickAddTag() : Add selected tag to taglist of user. Added tags are shown as toggle type buttons on My Page, User Page, Post Page and Lobby.
- onClickDeleteTag() : Delete selected tag from taglist of user.
- onClickFollowUser() : Add selected user to userlist of user.
- onClickUnfollowUser() : Delete selected user from userlist of user.

6. Lobby   
- onClickCreateNewRoom() : Create new chatroom and redirect to it with user in it.
- onToggleTag() : Toggle the clicked tag. Chatrooms with selected tags are shown to the user.
- onClickJoin() : Redirect to selected chatroom.
- onClickSure() : By answering yes to shallWe, user redirects to selected chatroom.
- onClickSorry() : By rejecting the shallWe, the chatroom disappears from the chatroom list. 

7. CreatePost   
- onClickCreatePost(image: image, content: content, tags: int) : Create new post and redirects to MyPage.
- onClickBack() : Alert user to decide whether to go back to MyPage. If user answers yes, redirects to MyPage. 


8. UserPage   
- onClickFollowUser() : Add selected user to userlist of user.
- onClickUnfollowUser() : Delete selected user from userlist of user.
- onClickUserPost() : Pop up the selected post with details.
- onClickBack() : Redirects to the page where user entered recent UserPage.

9. Chatroom   
- onToggleGlobal(): If toggled as Global, recent chatroom will be visible to everyone.
- onClickInvite(): Selected friend will get invitation to enter recent chatroom.
- onClickSendButton(): If message input is not blank, the content will be sent to Chat log.
- onClickRoomInfo(): Host can change chatroom name, game tag, maximum number of users through the ‘Room Information’ pop up.
   
   
#### [Components]

1. MenuBar   
- onClickPostButton(): Redirect to Post page.
- onClickLobbyButton(): Redirect to Lobby page.
- onClickSearchButton(): Redirect to Search page.
- onClickMyPageButton(): Redirect to Mypage page.

2. Profile   
onClickSetting(): Redirect to Setting page.

3. MainPost   
- onSendShallWe(): Send 'ShallWe' to author of the post.
- onClickLike(): increase likes of the post.
- onClickComment(content: string): Make new comment. Disabled if content is empty.

4. User   
- onClickAvatar(): Redirect to Userpage page of the selected user.

5. PostInUserPage   
- onClickEdit(): Redirect to CreatePost page of the selected post.
- onClickDelete(): Delete selected post.
- onClickComment(): Pop up comments list.

6. PostInGrid   
- onClickImage(): Pop up selected post.

7. Tag    
- onToggleTag(): Toggle state of selected tag.

8. RoomInfo   
- onChangeTag(): Select tag of the room.
- onChangeMaxUserNum(): Select maximum number of users in the room.
- onClickCreateNewRoom(): Make new Chatroom and redirect to the Chatroom page.

9. Comment   
- onClickEdit(): Change content of selected comment.
- onClickDelete(): Delete selected comment.


### Frontend Relations
<img width="525" alt="relation" src="https://user-images.githubusercontent.com/34087946/97771110-10b39a80-1b7d-11eb-8e90-2a795c45ca0d.png">

## Backend Design
Here is detailed specifications of RESTful APIs for backend design.   
<img width="861" alt="backend" src="https://user-images.githubusercontent.com/60940673/97517112-f55b5a80-19d7-11eb-8ee9-3408491f6616.png">


# Implementation Plan
The implementation plan gives the description of programming tasks divided by sprints and members. As all user stories are based on the corresponding pages, the plan is described based on pages as well. 
The pages are already divided considering dependencies in the UI: ‘Login’, ‘Posts’, ‘Lobby’, ‘Search’ and ‘MyPage’. We will develop ‘Login’ part in sprint 2 which uses Discord API. Then in sprint 3,’Posts’, ’Search’, and ‘MyPage’ part, mainly functioning as SNS service, will be developed. Finally, in sprint 4, ‘Lobby’ part that is related to chatroom will be developed.

![plan](https://user-images.githubusercontent.com/60940673/97444812-e80a8580-196f-11eb-89a7-2475b21f5864.png)

The implementation plan is specified by features as below. The estimated difficulty and time of plan are also indicated.

<img width="959" alt="스크린샷 2020-10-31 오전 12 30 45" src="https://user-images.githubusercontent.com/60940673/97724574-6ef66400-1b10-11eb-8af8-fefd35ad1e73.png">

# Testing Plan


## Unit Testing
All of components and modules should be tested automatically. We would test them with following tools in each sprint. Expected value of the code coverage is 90%.

React : Jest   
Django : Python unit test

## Functional Testing
All APIs should be tested automatically. In sprint 3, we would test APIs about authentication and posting. In sprint 4, we would test APIs about room and 'shallWe'. We would test them with following tools.

React : Jest   
Django : Python unit Test

## Acceptance & Integration Testing
Acceptance and integration testing would be done after implementation. In sprint 5, we would use Cucumber for acceptance testing with user stories in sprint 1. Also, we would use Trevis CI for Integration testing.

Acceptance Testing : Cucumber   
Integration Testing : Trevis CI
