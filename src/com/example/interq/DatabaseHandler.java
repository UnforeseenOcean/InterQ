package com.example.interq;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper{

    private static DatabaseHandler instance = null;
    private static final int DATABASE_VERSION = 1;    
    private static final String DATABASE_NAME = "interq";
    
    
    //QUESTION DB
    private static final String QUESTION_DATABASE_NAME = "questions";
    private static final String QUESTION_ID = "id";
    private static final String QUESTION_TOPIC = "topic";
    private static final String QUESTION_TITLE = "title";
    private static final String QUESTION_DESCRIPTION = "description";
    private static final String QUESTION_HINT = "hint";
    private static final String QUESTION_REVIEW_LINK = "review";
    private static final String QUESTION_ANSWER = "answer";
    private static final String QUESTION_TYPE = "type";
    
    private static final String QUESTION_TABLE_CREATE = 
            "CREATE TABLE IF NOT EXISTS "+ QUESTION_DATABASE_NAME+
            " ("+ 
            QUESTION_ID + " INTEGER PRIMARY KEY, "+
            QUESTION_TOPIC + " INTEGER, "+
            QUESTION_TITLE + " TEXT, " +
            QUESTION_DESCRIPTION + " TEXT, "+
            QUESTION_HINT + " TEXT, " +
            QUESTION_REVIEW_LINK + " TEXT, "+
            QUESTION_ANSWER + " TEXT, "+
            QUESTION_TYPE + " TEXT)"
                    ; 
    
    //SUBTOPIC DB
    private static final String SUBTOPIC_DATABASE_NAME = "subtopic";
    private static final String SUBTOPIC_ID = "id";
    private static final String SUBTOPIC_NAME = "name";
    private static final String SUBTOPIC_TOPIC_ID = "topic";
    
    private static final String SUBTOPIC_TABLE_CREATE = 
            "CREATE TABLE IF NOT EXISTS " + SUBTOPIC_DATABASE_NAME+
            " ("+
                    SUBTOPIC_ID + " INTEGER PRIMARY KEY, " +
                    SUBTOPIC_TOPIC_ID + " INTEGER, "+
                    SUBTOPIC_NAME + " TEXT)"
                    ;
    
    //REVIEW DB
    private static final String REVIEW_DATABASE_NAME = "review";
    private static final String REVIEW_TITLE = "title";
    private static final String REVIEW_DESCRIPTION = "description";
    private static final String REVIEW_ID = "id";
    private static final String REVIEW_SUBTOPIC_ID = "subtopic";
    
    private static final String REVIEW_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + REVIEW_DATABASE_NAME+
            " ("+
            REVIEW_ID + " INTEGER PRIMARY KEY, "+
            REVIEW_SUBTOPIC_ID + " INTEGER, " +
            REVIEW_TITLE + " TEXT, "+
            REVIEW_DESCRIPTION + " TEXT)"
            ;    
    
    //TOPIC DB

    private static final String TOPIC_DATABASE_NAME= "topic";
    private static final String TOPIC_ID = "id";
    private static final String TOPIC_NAME = "name";
    
    private static final String TOPIC_TABLE_CREATE = 
            "CREATE TABLE IF NOT EXISTS " + TOPIC_DATABASE_NAME+
            " ("+
                    TOPIC_ID+" INTEGER PRIMARY KEY, "+
                    TOPIC_NAME+" TEXT)"
                    ;
    
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        
    }

    
    public static DatabaseHandler getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHandler(context.getApplicationContext());
        }return instance;
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS " + TOPIC_DATABASE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SUBTOPIC_DATABASE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QUESTION_DATABASE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + REVIEW_DATABASE_NAME);
        
        db.execSQL(TOPIC_TABLE_CREATE);
        db.execSQL(QUESTION_TABLE_CREATE);
        db.execSQL(REVIEW_TABLE_CREATE);
        db.execSQL(SUBTOPIC_TABLE_CREATE);
        
        //TODO: FILL THE DBs
        this.addTopic(db,"Sorting");
        this.addTopic(db,"Data Structures");
        this.addTopic(db,"Graph Theory");
        this.addTopic(db,"Selected Topics");
        
        this.addSubTopic(db,"Sorting","Heap Sort");
        this.addSubTopic(db,"Sorting","Bubble Sort");
        this.addSubTopic(db,"Sorting","Selection Sort");
        this.addSubTopic(db,"Sorting","Merge Sort");
        this.addSubTopic(db,"Sorting","Quick Sort");
        this.addSubTopic(db,"Sorting","Bucket Sort");
        
        this.addSubTopic(db,"Data Structures","Arrays");
        this.addSubTopic(db,"Data Structures","Stacks");
        this.addSubTopic(db,"Data Structures","Queues");
        this.addSubTopic(db,"Data Structures","Linked Lists");
        this.addSubTopic(db,"Data Structures","Hash Tables");
        this.addSubTopic(db,"Data Structures","Binary Search Tree");

        this.addSubTopic(db,"Graph Theory","BFS");
        this.addSubTopic(db,"Graph Theory","DFS");
        this.addSubTopic(db,"Graph Theory","Topological Sort");
        this.addSubTopic(db,"Graph Theory","Maximum Flow");
        this.addSubTopic(db,"Graph Theory","Dijkstra");
        this.addSubTopic(db,"Graph Theory","Bellman Ford");
        this.addSubTopic(db,"Graph Theory","Shortest Path");

        this.addSubTopic(db,"Selected Topics","Dynamic Programming");
        this.addSubTopic(db,"Selected Topics","Amorized Analysis");
        this.addSubTopic(db,"Selected Topics","NP-Complete");
        this.addSubTopic(db,"Selected Topics","Concurrency");
        this.addSubTopic(db,"Selected Topics","Linear Programming");
        
        this.addQuestion(db, "Sorting", "Rotated Array",
                "Given a sorted array of n integers that has been rotated an unknown number of times, give an O(log n) " +
                "algorithm that finds an element in the array. You may assume that the array was originally sorted in " +
                "increasing order. You may also assume no duplicates\n def search(a,x) :  # (a is the array of integers," +
                " x is the integer you want to find)","modification of binary search", null, null, "coding");

        this.addQuestion(db, "Sorting", "Merge Arrays", "You are given two sorted arrays, A and B, and A has a large enough" +
        		" buffer at the end to hold B. Write a method to merge B into A in sorted order.\n def merge(a,b,n,m): " +
        		" #(a and b are arrays, n is the length of array b, m is the length of array a)"," Mergesort. You don�t need" +
        				" to copy contents of a after running out of b�s. They are already in place.", "Merge Sort",null, "coding");

        this.addQuestion(db, "Data Structures", "Anagrams"," Write a method to decide if two strings are anagrams or not.\n def " +
        		"anagram(s,t): # s and t are strings","Sort the strings",null, null, "coding");

        this.addQuestion(db,"Data Structures", "Unique Characters", "Implement an algorithm to determine if a string has all unique " +
        		"characters. Try to find one of O(n) time.\n def isUniqueChars2(string):","hint", null, null, "coding");

        this.addQuestion(db, "Graph Theory", "Binary Search Tree", "On a binary search tree, we insert a value v, by comparing" +
        		" it to the root. If v > root, we go ___.", null, "Binary Search Tree","right", "fill in");

        this.addQuestion(db, "Graph Theory","Depth-First Search"," In a depth-first search of an undirected graph G, every edge" +
        		" of G is either a tree edge or a __ edge.","hint","DFS","back", "fill in");

        this.addQuestion(db, "Selected Topics","DP vs. ?"," ____ is fundamentally a top-down computation and Dynamic Programming is " +
        		"fundamentally bottom-up","hint", null, "Memoization","fill in");

        this.addQuestion(db, "Selected Topics","Bellman-Ford"," Bellman-Ford algorithm _____ edges, progressively decreasing an estimate " +
        		"v.d on the weight of a shortest path from the source s to each vertex v (element of) V until it achieves the actual " +
        		"shortest-path weight (delta)(s,v).","hint", "Bellman-Ford","relaxes", "fill in");

        //subtopic, title, description

        this.addReview(db,"Bubble Sort","Run Time","At worse case, bubble sort runs in O(n^2)");
        this.addReview(db,"Bubble Sort","What is?","Start at the beginning of an array and swap the first two elements if the first is bigger than the second. Go to the next pair, etc, continuously making sweeps of the array until sorted.");

        this.addReview(db,"Selection Sort","Run Time","At worse case, selection sort runs in O(n^2)");
        this.addReview(db,"Selection Sort","What Is?"," Find the smallest element using a linear scan and move it to the front. Then, find the second smallest and move it, again doing a linear scan. Continue doing this until all the elements are in place.");

        this.addReview(db,"Merge Sort","Run Time","At expected and worse case, merge sort runs in O(nlgn)");
        this.addReview(db,"Merge Sort","What is?","Sort each pair of elements. Then sort every fur elements by merging every two pairs. Then sort every 8 elements, etc.");

        this.addReview(db,"Quick Sort","Run Time","At expected quick sort runs in O(nlgn) and O(n^2) in worse case");
        this.addReview(db,"Quick Sort","What is?","Pick a random element and partition the array, such that all numbers that are less than it come before all elements that are greater than it. Then do that for each half, then each quarter, etc");

        this.addReview(db,"Heap Sort","Run Time","At worse case, heap sort runs in O(nlgn)");
        this.addReview(db,"Heap Sort","What is?","Repeatedly remove the largest element from the heap, and insert it into the array");

        this.addReview(db,"Bucket Sort","Run Time","This runs in time of O(n+m), where n is the number of items and m is the number of distinct items.");
        this.addReview(db,"Bucket Sort","What is?","Partition the array into a finite number of buckets, and then sort each bucket individually");

    }

    
    public void addTopic(SQLiteDatabase db, String name){
        ContentValues values = new ContentValues();
        values.put(TOPIC_NAME, name);
        db.insert(TOPIC_DATABASE_NAME,null, values); 
            }
    
    public void addSubTopic(SQLiteDatabase db, String topic, String name){
        ContentValues values = new ContentValues();
        values.put(SUBTOPIC_NAME, name);
        int topicNumber = getTopicId(db, topic);
    
        values.put(SUBTOPIC_TOPIC_ID, topicNumber);
        values.put(SUBTOPIC_NAME, name);
        
        db.insert(SUBTOPIC_DATABASE_NAME, null, values);
        
    }
    
    public void addQuestion(SQLiteDatabase db, String topic, String title,
            String description, String hint, String link, String answer,
            String type){
        
        ContentValues values = new ContentValues();
        values.put(QUESTION_TITLE, title);
        values.put(QUESTION_DESCRIPTION, description);
        values.put(QUESTION_HINT, hint);
        values.put(QUESTION_REVIEW_LINK, link);
        values.put(QUESTION_ANSWER, answer);
        values.put(QUESTION_TYPE, type);
        
        int topicId = getTopicId(db, topic);
        values.put(QUESTION_TOPIC, topicId);
        
        db.insert(QUESTION_DATABASE_NAME,null, values);
    }
    
    public void addReview(SQLiteDatabase db, String subTopic, String title, String description){
        ContentValues values = new ContentValues();
        values.put(REVIEW_TITLE, title);
        values.put(REVIEW_DESCRIPTION, description);
        
        int id = getSubTopicId(db, subTopic);
        values.put(REVIEW_SUBTOPIC_ID, id);
        
        
        db.insert(REVIEW_DATABASE_NAME,null,values);
    }
    
    public static Integer getTopicId(SQLiteDatabase db, String topic){

        Cursor data = db.rawQuery("SELECT id FROM topic WHERE "+TOPIC_NAME+"=?", new String[] {topic} );
        data.moveToFirst();
        
        return data.getInt(data.getColumnIndex(TOPIC_ID));
    }

    public static Integer getSubTopicId(SQLiteDatabase db, String subTopic){
        
        String cmd = "SELECT id FROM subtopic WHERE "+SUBTOPIC_NAME+"=?";
        
        Cursor data = db.rawQuery(cmd,new String[] {subTopic});
        data.moveToFirst();
        return data.getInt(data.getColumnIndex(SUBTOPIC_ID));
    }
  
    
    
    
    
    
    

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        
    }
    
    
    
}
