import React, { useState, useEffect } from 'react';
import Post from '../Post/Post';
import PostFrom from '../Post/PostForm';

function Home() {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [postList, setPostList] = useState([]);

    const refreshPost = () => {
        fetch("/posts")
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setPostList(result);
                },
                (error) => {
                    console.log("Error : ", error);
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }

    useEffect(() => {
        refreshPost();
    }, [postList])

    if (error) {
        return <div> Error! </div>
    } else if (!isLoaded) {
        return <div> Loading... </div>
    } else {
        return (
            <div
                style={{
                    display: "flex",
                    flexWrap: "wrap",
                    justifyContent: "center",
                    alignItems: "center",
                    backgroundColor: "#f0f5ff"
                }}>
                <PostFrom userId={1} userName={"antozy"} refreshPost={refreshPost} />
                {postList.map(post => (
                    <Post
                        userId={post.userId}
                        userName={post.userName}
                        title={post.title}
                        text={post.text}
                        postId={post.id}
                        likes={post.likes}
                    ></Post>
                ))}
            </div>
        );
    }

}

export default Home;