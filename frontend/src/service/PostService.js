import axios from "axios";
import { APIConstants } from "../constants/api";

const api = axios.create({
  baseURL: `${APIConstants.BACKEND_BASE_URL}/posts`,
});

const getPosts = (token) =>
  api.get("/all", {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

const createPost = (post, token) =>
  api.post(
    "/",
    {
      content: post.content,
      likedUsers: []
    },
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );

const likePost = (id, token) =>
  api.put(
    `${id}/like`,
    {},
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );

export { getPosts, createPost, likePost };
