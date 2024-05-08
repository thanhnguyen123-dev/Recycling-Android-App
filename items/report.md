# GP-24s1 Report

The following is a report template to help your team successfully provide all the details necessary for your report in a structured and organised manner. Please give a straightforward and concise report that best demonstrates your project. Note that a good report will give a better impression of your project to the reviewers.

Note that you should have removed ALL TEMPLATE/INSTRUCTION texts in your submission (like the current sentence), otherwise it hampers the professionality in your documentation.

*Here are some tips to write a good report:*

* `Bullet points` are allowed and strongly encouraged for this report. Try to summarise and list the highlights of your project (rather than give long paragraphs).*

* *Try to create `diagrams` for parts that could greatly benefit from it.*

* *Try to make your report `well structured`, which is easier for the reviewers to capture the necessary information.*

*We give instructions enclosed in square brackets [...] and examples for each sections to demonstrate what are expected for your project report. Note that they only provide part of the skeleton and your description should be more content-rich. Quick references about markdown by [CommonMark](https://commonmark.org/help/)*

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Application Description](#application-description)
4. [Application UML](#application-uml)
5. [Application Design and Decisions](#application-design-and-decisions)
6. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
7. [Testing Summary](#testing-summary)
8. [Implemented Features](#implemented-features)
9. [Team Meetings](#team-meetings)
10. [Conflict Resolution Protocol](#conflict-resolution-protocol)

## Administrative
- Firebase Repository Link: <insert-link-to-firebase-repository>
    - Confirm: I have already added comp21006442@gmail.com as a Developer to the Firebase project prior to the due date.
- Two user accounts for markers' access are usable on the app's APK (do not change the username and password unless there are exceptional circumstances. Note that they are not real e-mail addresses in use):
    - Username: comp2100@anu.edu.au  Password: comp2100
    - Username: comp6442@anu.edu.au  Password: comp6442

## Team Members and Roles
The key area(s) of responsibilities for each member

| UID   |  Name  |   Role |
|:------|:------:|-------:|
| u7724204 | Julius Liem | Programmer |
| u7650334 | Harrison Black | Programmer |
| u7761531 | Devansu Yadav | Programmer |
| u7594144 | Le Thanh Nguyen | Programmer |


## Summary of Individual Contributions

Specific details of individual contribution of each member to the project.

Each team member is responsible for writing **their own subsection**.

A generic summary will not be acceptable and may result in a significant lose of marks.

*[Summarise the contributions made by each member to the project, e.g. code implementation, code design, UI design, report writing, etc.]*

*[Code Implementation. Which features did you implement? Which classes or methods was each member involved in? Provide an approximate proportion in pecentage of the contribution of each member to the whole code implementation, e.g. 30%.]*

*you should ALSO provide links to the specified classes and/or functions*
Note that the core criteria of contribution is based on `code contribution` (the technical developing of the App).

*Here is an example: (Note that you should remove the entire section (e.g. "others") if it is not applicable)*

1. **u7724204, Julius**  I have xx% contribution, as follows: <br>
- **Code Contribution in the final App**
    - Login Feature (State Design Pattern, Singleton Design Pattern) - class LoginState: [LoginState.java](link_to_file), class LoginContext, class LoggedInState, class LoggedOutState, class LoginActivity
    - DAO design pattern -  class RecycledItemDAO: [RecycledItemDAO.java](link_to_file), class RecycledItemDAOJsonImp: [RecycledItemDAOJsonImp.java](link_to_file)
    - RecycledItem model class - [RecycledItem.java](link_to_class)
    - RecycledItemDb class (Observer design pattern) - [RecycledItemDb.java](link_to_class)
    - Main app layout- MainActivity class: [MainActivity.java](link_to_file), MainActivity layout: [MainActivity.xml](link_to_file)

- **Code and App Design**
    - DAO design pattern, Observer design pattern, Singleton design pattern*
    - [UI Design. Specify what design did the involved member propose? What tools were used for the design?]* <br><br>

- **Others**: (only if significant and significantly different from an "average contribution")
    - [Report Writing?] [Slides preparation?]*
    - [You are welcome to provide anything that you consider as a contribution to the project or team.] e.g., APK, setups, firebase* <br><br>

**u7650334, Harrison**  I have xx% contribution, as follows: <br>
- **Code Contribution in the final App**
    - Cart feature (Singleton Design Pattern) - class Cart: [Cart.java](link_to_file),
    - Cart Layout - CartActivity class = [CartActivity.java](link_to_file), CartActivity layout: [CartActivity.xml](link_to_file)
    - Firebase based DAO - class RecycledItemDAO: [RecycledItemDAO.java](link_to_file), class RecycledItemDAOJsonImp: [FirebaseRecycledItemDAO.java](link_to_file)
    - Maps implementation (should be done soon)
    - Map layout (Should be done soon)

- **Code and App Design**
    - Singleton design pattern, DAO design pattern

3. **u7761531, Devansu**  I have xx% contronalityias follows: <br>
- **Code Contribution in the final App**
- Data Stream - `startStream()` and `stopStream()` methods in [RecycledItemDb.java](link_to_file), `onCreate()`, `update()` and `onDestroy()` methods in [MainActivity.java](link_to_file)
- Search functionality (Tokenizer, Search Query Parser, Search Query evaluation) - class [Token.java](link_to_file), class [Tokenizer.java](link_to_file), class [SearchQueryParser.java](link_to_file), class [`SearchExp.java`](link_to_file), `onCreate()` method in [`MainActivity.java`](link_to_file), `search()` method in [`RecycledItemDB.java`](link_to_file)
- Main app layout - MainActivity class: [MainActivity.java](link_to_file), MainActivity layout: [MainActivity.xml](link_to_file)
- Unit Tests - class [TokenizerTest.java](link_to_file), class [SearchQueryParserTest.java](link_to_file)

- **Code and App Design**
- [UI Design. Specify what design did the involved member propose? What tools were used for the design?]* <br><br>

- **Others**: (only if significant and significantly different from an "average contribution")
    - [Report Writing?] [Slides preparation?]*
    - [You are welcome to provide anything that you consider as a contribution to the project or team.] e.g., APK, setups, firebase* <br><br>
  

4. **u7594144,Thanh** I have xx% contribution, as follows: <br>
- **Code Contribution in the final App**
    - Login Feature (Firebase Authentication) - class LoginState: [LoginState.java](link_to_file), class LoginContext, class LoggedInState, class LoggedOutState, class LoginActivity, class LogUtil
    - Signup Feature (Firebase Authentication) - class SignupFragment: [SignupFragment.java](link_to_file)
    - Private Chat Feature (Firebase Realtime Databases) - class Message: [Message.java](link_to_class), class MessageAdapter: [MessageAdapter.java](link_to_class), class UserAdapter: [UserAdapter.java](link_to_class), class ChatsMainActivity: [ChatsMainActivity.java](link_to_class), class DirectMessageActivity: [DirectMessageActivity.java]
    - Data-Profile Feature - class ProfileActivity: [ProfileActivity.java]



- **Code and App Design**
    - DAO design pattern, Observer design pattern, Singleton design pattern*
    - [UI Design. Specify what design did the involved member propose? What tools were used for the design?]* <br><br>

- **Others**: (only if significant and significantly different from an "average contribution")
    - [Report Writing?] [Slides preparation?]*
    - [You are welcome to provide anything that you consider as a contribution to the project or team.] e.g., APK, setups, firebase* <br><br>

## Application Description

*[What is your application, what does it do? Include photos or diagrams if necessary]*

This is a recycling logging application. By using this application, we can log what we have recycled in each day, and we can see the statistics and history of our recycling history. This app also allows the users to see the nearest recycling spot. By logging in, the user can add their log, add their own personal recycled items, as well as see their statistics. They can also see the nearest location of recycling points.

### Application Use Cases and or Examples

Target users: people who want to log their recycling activities.

Molly wants to log her recycling activities.
1. Molly decides that she will embrace green lifestyle.
2. Molly downloads the RecycleMe app.
3. Now, Molly can: log her recycling activities everyday
4. By the end of the month, Molly can see how many items she has recycled.
5. Molly can also use it to find the nearest recycling points.

Target users: people who recycle large amounts of recycling, or do not have access to recycling bins at home.

Steve wants to recycle some stuff. (Will be possible soon)
1. Steve wants to recycle some items at a recycling plant.
2. Steve opens the app.
3. The app tells him where nearby recycling facilities are.

*Here is a map navigation application example*

*Targets Users: Drivers*

* *Users can use it to navigate in order to reach the destinations.*
* *Users can learn the traffic conditions*
* ...

*Target Users: Those who want to find some good restaurants*

* *Users can find nearby restaurants and the application can give recommendations*
* ...

*List all the use cases in text descriptions or create use case diagrams. Please refer to https://www.visual-paradigm.com/guide/uml-unified-modeling-language/what-is-use-case-diagram/ for use case diagram.*

<hr> 

### Application UML

![ClassDiagramExample](media/_examples/ClassDiagramExample.png) <br>
*[Replace the above with a class diagram. You can look at how we have linked an image here as an example of how you can do it too.]*

<hr>

## Code Design and Decisions

This is an important section of your report and should include all technical decisions made. Well-written justifications will increase your marks for both the report as well as for the relevant parts (e.g., data structure). This includes, for example,

- Details about the parser (describe the formal grammar and language used)

- Decisions made (e.g., explain why you chose one or another data structure, why you used a specific data model, etc.)

- Details about the design patterns used (where in the code, justification of the choice, etc)
- The State design pattern is used in the LoginContext class. Justification of this include:
1. There are different actions that can be done depending on whether the user is logged in or logged out (e.g. when the user is logged out then the user can't add item to his cart history)
- The DAO design pattern is used in reading the data instances. Justification of this are:
1. DAO design pattern is extensible, allowing the application to read the data from multiple sources

*Please give clear
* Used for storing all of the Recycled Items in the MainActivity.class
* Storing log data using a NodeData class
* Code Locations: defined in [Class AVLTree](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/cart/AVLTree.java), used in [SearchExp.class, method evaluateSearchExp()](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/search/SearchExp.java?ref_type=heads#L25-L46), [AVLTreeItem.class](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/cart/AVLTreeItem.java?ref_type=heads), and [UserTree.class]()
* Reasons:
    * We use AVLTree for storing the items because searching items in an AVLTree is faster compared to searching it in an ArrayList.


3. *ArrayList*
    * Objective:
        * Used for storing Message entity objects in MessageAdapter.class
        * Used for storing User entity objects in UserAdapter.class
        *
    * Code Locations: [AVLTree.class, flattenTree() method](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/cart/AVLTree.java?ref_type=heads#L227-L239), [AVLTreeItem.class](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/cart/AVLTreeItem.java?ref_type=heads), [FirebaseRecycledItemDAO.java](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/dao/FirebaseRecycledItemDAO.java?ref_type=heads), [ All of the adapter
    * Reasons:
        * We use ArrayList to store entity objects in an Adapter that binds the data to the ViewHolder. All of our adapter use ArrayList because it is the most convenient data structure for RecyclerView. If we use other kind of data structure, we would have to implement an iterator for it (and it’s not necessarily faster because in the end we would have to traverse the whole data).

### <u>Grammar(s)</u>
*[How do you design the grammar? What are the advantages of your designs?]*
*If there are several grammars, list them all under this section and what they relate to.*

We designed the grammar by first identifying what relevant information the user will want to search on the search bar. We ended up on item name, item brand and item material. We also thought there should be an "and" token to allow complex search queries.

Advantages of the design

Expressiveness: The grammar allows for a wide range of valid search queries, including single-item queries, compound queries with conjunctions, and queries combining different search criteria (e.g., item name and brand).

Flexibility: Users can input various types of queries, such as searching for specific items, filtering by brand or material, or combining multiple criteria in a single query.
Clarity: The grammar provides clear rules for constructing valid search queries, making it easy for us to understand and implement search functionality.
Scalability: The grammar can be extended to accommodate additional search criteria or features as the application evolves. For example, new types of recyclable items or search filters can be added without significant changes to the grammar.
User-Friendly: By allowing users to input flexible and intuitive search queries, the grammar enhances the usability of the search bar, leading to a better overall user experience.

Production Rules:

   ```
   <SearchQuery>  ::=  <Item><Conjuction><Tag> | <Item> | <Tag>
   <Item>         ::=  "<String>"
   <String>       ::=  <Char> | <Char><String>
   <Char>         ::=  a | b | c | d | e | f | g | h | i | j | k | l | m | n | o | p | q | r | s | t | u | v | w | x | y | z
   <Conjunction>  ::=  "and"
   <Tag>          ::=  "#" <Item> | "@" <Item> | <Tag><Conjunction><Tag>
   ```

### <u>Tokenizers and Parsers</u>

*[Where do you use tokenizers and parsers? How are they built? What are the advantages of the designs?]*

We used tokenizers and parser in a search bar that allows the user filter out and find the specific recycled item they need.

<u>Tokenizer</u>

Our tokenizer is encapsulated within the [Tokenizer](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/search/Tokenizer.java?ref_type=heads) class, which contains the logic for extracting tokens from the input search query.

When we instantiate the Tokenizer class with a search query, and extract the first token using the `extractNextToken()` method.

In the `extractNextToken()` method, we follow a sequence of steps to identify and extract different types of tokens based on the above described grammar. We check the first character to determine the type of token (brand tag, material tag, conjunction, or item name). 
Based on the type of token, we invoke specific helper methods (`scanTag()`, `scanString()`, `checkAnd()`) to extract the token. After extracting the token, we update the `currentToken` variable and process the remaining portion of the search query.

Our tokenizer includes helper methods like `scanTag()` to scan for brand or material tags, `scanString()` to scan for item names, and `checkAnd()` to check for conjunctions.

We have also implemented error handling in our tokenizer. If we encounter an invalid character in the search query, we throw an exception to ensure robust error handling.

<u>Advantages of our tokenizer</u>
- The tokenizer has been designed in a way that allows our parser to fetch tokens on-demand when parsing a search query instead of extracting all tokens at once 
before parsing the query. This allows for a fast, and efficient way of processing queries both syntactically and semantically.
- Modularized and scalable to extract different types of tokens based on the grammar
- Invalid query filtering even before query parsing.

<u>Parser</u>
Our Parser has been implemented in the [SearchQueryParser](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/search/SearchQueryParser.java?ref_type=heads) class. 
By design, it has implemented as a Recursive Descent Parser, breaking down into each production rule of our grammar, parsing tokens from a user's search query 
in a left-to-right fashion. 

The parser parses different types of tokens extracted from the search query using the tokenizer, and efficiently checks for grammar violations and invalid search queries before parsing the rest of the query.
The `parseSearchQuery()` method is what we use to invoke the parser which generates a search expression as described using the [SearchExp.java](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/search/SearchExp.java?ref_type=heads) class.

The search expression after an entire query has been parsed consists of the parsed item name, list of brand names and materials. This parsed query is then
evaluated using the `evaluateSearchExp()` method in the [SearchExp.java](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/search/SearchExp.java?ref_type=heads#L25-L46) class which
searches for the user requested item in an AVL tree using a fast theta(log n) implementation.

<u>Advantage of the Parser</u>
- The query parser has been designed to process quite complex and powerful queries like searching for recycled items across multiple brands and materials providing 
a lot of flexibility to users.
- The parser can process and evaluate search queries in a fast and efficient manner.
- Effectively reports invalid search queries based on grammar violations.
- Easily scalable to handle more complex grammars allowing for even more powerful search queries.

1. *HashMap*
    * Objective: used for storing the items in a cart
    * Code Locations: defined in [Class CartActivity, methods CartActivity(), addItem(), removeItem()](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/cart/Cart.java) processed using [setChartMaterials() and setChartRecycledItemOverTime() in class StatisticActivity](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/StatisticActivity.java)
    * *Reasons:*
        * We use HashMap because we need to categorize the RecycleItem based on their material. In order to do that, we need to have some sort of a Key-Value Pair
        * We don't need to access the item by index for the [Data-Graphical] feature because all of the data will be traversed
        * For the (part), the data ... (characteristics) ...

2. *AVLTree*
    * Objective: used for storing previously recycled items.
    * Code Locations: defined in [Class AVLTree](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/cart/AVLTree.java), used in [SearchExp.class, method evaluateSearchExp()](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/search/SearchExp.java?ref_type=heads#L25-L46), [AVLTreeItem.class](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/cart/AVLTreeItem.java?ref_type=heads), and [UserTree.class](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/RecycleMe/app/src/main/java/com/example/recycleme/cart/UserTree.java?ref_type=heads)
    * Explanations: For
    * Reasons:
        * Using AVLTree guarantees a faster searching for recycled items. It has a theta(n) time complexity
        * We use AVLTree to

3. *ArrayList*
    * *Objective:*
        * *used for storing the items in the HashMap that's used in the Cart*
        * *used for showing the data in a RecyclerView*
    * Reasons:
        * We use ArrayList to store Entity objects in the Adapter that binds data to the ViewHolder. The Adapter then create a RecyclerView to reuse old instances of Views to display a responsive and dynamic list in the UI

<hr>

### Design Patterns
*[What design patterns did your team utilise? Where and why?]*

1. *Singleton Pattern*
    * *Objective: used for storing the cart for the ability to view the cart*
    * *Code Locations: defined in [Cart, getInstance()](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and [class AnotherClass, lines l1-l2](url); processed using [dataStructureHandlerMethod](url) and ...
    * *Reasons:*
        * ...
2. Observer Pattern
   Objective: used for storing and notifying observers about changes in the list of recycled items in the RecycledItemDb feature.
    * *Code Locations: defined in [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and [class AnotherClass, lines l1-l2](url); processed using [dataStructureHandlerMethod](url) and ...
      Reasons:
      -The Observer pattern allows for a design where the RecycledItemDb class can notify multiple observers (such as UI components or logging systems) about changes in the list of recycled items without those observers needing to know the details of the RecycledItemDb implementation.
      -It allows new observers to be added without modifying the subject (RecycledItemDb) class.
      -This pattern promotes reusability by separating concerns, making it easier to maintain and understand the codebase.


<hr>


### Others

*[What other design decisions have you made which you feel are relevant? Feel free to separate these into their own subheadings.]*
*FirebaseUtil*
*

<br>
<hr>

## Implemented Features
*[What features have you implemented? where, how, and why?]* <br>
*List all features you have completed in their separate categories with their featureId. THe features must be one of the basic/custom features, or an approved feature from Voice Four Feature.*

### Basic Features
1. [LogIn]. Description of the feature ... (easy)
    * Code: [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
    * Description of feature: ... <br>
    * Description of your implementation: ... <br>

2. [DataFiles]. Description  ... ... (...)
    * Code to the Data File [users_interaction.json](link-to-file), [search-queries.xml](link-to-file), ...
    * Link to the Firebase repo: ...

3. ...
   <br>

### Custom Features
Feature Category: Privacy <br>
1. [Privacy-Request]. Description of the feature  (easy)
    * Code: [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
    * Description of your implementation: ... <br>
      <br>

2. [Privacy-Block]. Description ... ... (medium)
   ... ...
   <br><br>

Feature Category: Firebase Integration <br>
3. [FB-Auth] Description of the feature (easy)
    * Code: [Class X, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
    * [Class B](../src/path/to/class/file.java#L30-85): methods A, B, C, lines of code: 30 to 85
    * Description of your implementation: ... <br>

Feature Category: Firebase Integration <br>
4. [FB-Persist] Persists data within app (medium)
    * Code: [Class FirebaseRecycledItemDAO, method getAllRecycledItemsHelper()](link_to_file)
    * Pulls data for the data stream from firebase storage as a json file and processes it as normal. <br>

<hr>

### Surprise Features

- If implemented, explain how your solution addresses the task (any detailed requirements will be released with the surprise feature specifications).
- State that "Surprised feature is not implemented" otherwise.

<br> <hr>

## Summary of Known Errors and Bugs

*[Where are the known errors and bugs? What consequences might they lead to?]*
*List all the known errors and bugs here. If we find bugs/errors that your team does not know of, it shows that your testing is not thorough.*

*Here is an example:*

1. *Bug 1:*
    - *A space bar (' ') in the sign in email will crash the application.*
    - ...

2.  *Bug 2:*
- *Opening the Main Page for the first time causes the application to freeze as firebase downloads the required data.*
3. ...

<br> <hr>


## Testing Summary

*[What features have you tested? What is your testing coverage?]*
*Please provide some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

*Here is an example:*

1. Tests for Search
    - Code: [TokenizerTest Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java) for the [Tokenizer Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43)
    - *Number of test cases: ...*
    - *Code coverage: ...*
    - *Types of tests created and descriptions: ...*

2. xxx

...

<br> <hr>


## Team Management

### Meetings Records
* Link to the minutes of your meetings like above. There must be at least 4 team meetings.
  (each committed within 2 days after the meeting)
* Your meetings should also have a reasonable date spanning across Week 6 to 11.*


- *[Team Meeting 1](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/items/meeting_1_report.md)*
- *[Team Meeting 2](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/items/meeting_2_report.md)*
- *[Team Meeting 3](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/items/meeting_3_report.md)*
- *[Team Meeting 4](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/items/meeting_4_report.md)*
- *[Team Meeting 5](https://gitlab.cecs.anu.edu.au/u7724204/gp-24s1/-/blob/main/items/meeting-5-report.md)*

<hr>

### Conflict Resolution Protocol
*[Write a well defined protocol your team can use to handle conflicts. That is, if your group has problems, what is the procedure for reaching consensus or solving a problem?
(If you choose to make this an external document, link to it here)]*

-Disagreement on project direction: schedule meeting, discuss perspective, vote on best approach.

-If someone slows down: reach out to them, empathise with their situation.  If unresponsive, give a deadline.

-If there is a fight/personality disagreement: Uninvolved team members calm things down and help find solutions.



