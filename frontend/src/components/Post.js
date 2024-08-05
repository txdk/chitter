import React, { useState, useContext } from "react";
import { likePost } from "../service/PostService";
import UserContext from "../context/UserContext";
import "../styles.css";

export default function Post({ post, dispatch }) {
  const providerValue = useContext(UserContext);
  const userInfo = providerValue.userInfo;
  const setUserInfo = providerValue.setUserInfo;

  const [liked, setLiked] = useState(
    post.likedUsers.map((user) => user.username).includes(userInfo.username)
  );

  const handleAddLike = () => {
    likePost(post.id, userInfo.token)
      .then((result) => {
        dispatch({
          type: "edit",
          payload: result.data,
        });
        setLiked(!liked);
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setUserInfo({
            username: null,
            role: null,
            token: null,
          });

          alert("Your session has expired!");
        }
        console.error(error);
      });
  };

  return (
    <div className="post">
      <p>
        <b>{`${post.user.username}: `}</b> {post.content}
      </p>
      <div>
        <div>
          <button
            className={liked ? "active-like-button" : "like-button"}
            onClick={handleAddLike}
          >
            👍
          </button>
          {post.likedUsers.length > 0 ? ` ${post.likedUsers.length}` : <></>}
        </div>
        <div className="tags">
          <p>{post.tags ? post.tags.map((tag) => `#${tag} `) : ""}</p>
        </div>
      </div>
    </div>
  );
}
