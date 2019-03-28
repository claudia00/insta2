package com.example.insta_droid1.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.example.insta_droid1.Post;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {


    @Override
    protected void queryPosts() {
        super.queryPosts();

        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        //to include more details on description
        postQuery.include(Post.KEY_USER);

        postQuery.setLimit(20);

        postQuery.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());

        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);

        //getting the info from database
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error with query");
                    e.printStackTrace();
                    return;
                }
                mPosts.addAll(posts);
                adapter.notifyDataSetChanged();

                for (int i = 0; i < posts.size(); i++) {
                    Post post = posts.get(i);
                    Log.d(TAG, "Post: " + post.getDescription() + ", Username: " + post.getUser().getUsername());
                }
            }
        });

    }


}
