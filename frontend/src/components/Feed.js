import React, { useEffect, useContext, useCallback } from "react";
import "../styles.css";
import { getPosts } from "../service/PostService";
import Post from "./Post";
import UserContext from "../context/UserContext";

export default function Feed({ state, dispatch }) {

  const providerValue = useContext(UserContext);
  const userInfo = providerValue.userInfo;
  const setUserInfo = providerValue.setUserInfo;

  const fetchPosts = useCallback(() => {
    getPosts(userInfo.token)
      .then((result) => {
        dispatch({
          type: "sync",
          payload: result.data,
        });
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setUserInfo({
            username: null,
            role: null,
            token: null
          });

          alert("Your session has expired!");
          
        }
        console.error(error);
      });
  }, [userInfo, setUserInfo, dispatch]);

  useEffect(() => {
    fetchPosts();
  }, [fetchPosts]);

  return (
    <div className="feed">
      <h1>📢 Your Feed</h1>
      <button className="button" onClick={fetchPosts}>
        ↻ Refresh
      </button>
      {state.posts.length === 0 ? (
        <p>Nothing to see here!</p>
      ) : (
        state.posts.map((post) => <Post post={post} key={post.id}></Post>)
      )}
    </div>
  );
}