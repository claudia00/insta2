package com.example.insta_droid1.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.insta_droid1.Post;
import com.example.insta_droid1.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class PostsFragment extends Fragment {

    public static final String TAG = "PostsFragment";

    private RecyclerView rvPosts;

//onCreateView inflate in the view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
                //super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvPosts = view.findViewById(R.id.rvPosts);

        //create the adapter
        //create the data source
        //set the adapter on the recycler view
        //set the layout manager on the recycler view
        queryPosts();

        //super.onViewCreated(view, savedInstanceState);
    }
    private void queryPosts(){

        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        //to include more details on description
        postQuery.include(Post.KEY_USER);

        //getting the info from database
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e!= null){
                    Log.e(TAG, "Error with query");
                    e.printStackTrace();
                    return;
                }

                for(int i = 0; i< posts.size(); i++){
                    Post post = posts.get(i);
                    Log.d(TAG, "Post: " + post.getDescription()+ ", Username: " + post.getUser().getUsername());
                }
            }
        });

    }

}
