import React from "react";
import "../styles.css";

export default function Post({ post }) {
  return (
    <div className="post">
      <p>
        <b>{`${post.user.username}: `}</b> {post.content}
      </p>
      <div className="tags">
        <p>{post.tags ? post.tags.map((tag) => `#${tag} `) : ""}</p>
      </div>
    </div>
  );
}
