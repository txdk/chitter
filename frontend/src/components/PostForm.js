import React, { useState, useContext } from "react";
import { createPost } from "../service/PostService";
import UserContext from "../context/UserContext";
import "../styles.css";

export default function PostForm({ state, dispatch }) {
  const [content, setContent] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const providerValue = useContext(UserContext);
  const userInfo = providerValue.userInfo;
  const setUserInfo = providerValue.setUserInfo;

  const clearForm = () => {
    setContent("");
  }

  const handleSubmit = (e) => {
    
    e.preventDefault();

    if (content.length === 0) {
      setErrorMessage("Post content cannot be empty.");
      return;
    }

    const postData = {
        user: {
          username: userInfo.username
        },
        content: content
    };

    createPost(postData, userInfo.token)
      .then((response) => {
        dispatch({
          type: "create",
          payload: response.data,
        });

        console.log(response);
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

    setErrorMessage("");
    clearForm();
  };

  return (
    <form className="post-form" onSubmit={handleSubmit}>
      <h2>💭 Share your thoughts!</h2>
      <textarea
        className="post-form-text"
        value={content}
        onChange={(e) => setContent(e.target.value)}
      ></textarea>
      {
        errorMessage? <div>
          <p className="failure-text">{errorMessage}</p>
        </div> : <></>
      }
      <div>
        <button className="button" type="submit">
          Post!
        </button>
      </div>
    </form>
  );
}