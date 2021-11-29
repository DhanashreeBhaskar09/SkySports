# Sky Sports News App

# Getting Started
- The application has a MVVM design pattern.

# UI 
- The ui in the application is kept fairly simple.
- Activities and viewmodels extend a common base class, namely BaseActivity and BaseViewModel respectively.
- Adapter and NewsActivity consists of a commented piece of code to download images from url and push it to the ImageView widget.
 
# Model class
- Current implementation of model class has duplicate properties. 
- Attempt was made to improve this further by implementing polymorphic serialization. 
- However, due to the ambiguity issues between the gradle build dependencies which will take time to resolve, current approach was implemented. 
- Strongly recommend to refer com.example.master.repository.model.DataClass which consists of the polymorphic serialization approach for existing model classes. 
