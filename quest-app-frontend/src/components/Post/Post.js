import React, { useState, useEffect, useRef } from 'react';
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import FavoriteIcon from '@mui/icons-material/Favorite';
import CommentIcon from '@mui/icons-material/Comment';
import { Link } from 'react-router-dom';
import { Container } from '@mui/material';
import Comment from '../Comment/Comment';
import CommentForm from '../Comment/CommentForm';

const ExpandMore = styled((props) => {
    const { expand, ...other } = props;
    return <IconButton {...other} />;
})(({ theme, expand }) => ({
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
        duration: theme.transitions.duration.shortest,
    }),
}));


function Post(props) {

    const { userId, userName, title, text, postId, likes } = props;

    const [expanded, setExpanded] = React.useState(false);
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [commentList, setCommentList] = useState([]);
    const [isLiked, setIsLiked] = useState(false);
    const [likeCount, setLikeCount] = useState(likes.length);
    const [likeId, setLikedId] = useState(null);
    const isInitialMount = useRef(true);
    let disabled = localStorage.getItem("currentUser") == null ? true : false;

    const handleExpandClick = () => {
        setExpanded(!expanded);
        refreshComments();
        console.log(commentList);
    };

    const handleLike = () => {
        setIsLiked(!isLiked);
        if (!isLiked) {
            saveLike();
            setLikeCount(likeCount + 1);
        }
        else {
            deleteLike();
            setLikeCount(likeCount - 1);
        }
    }

    const refreshComments = () => {
        fetch("/comments?postId=" + postId)
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setCommentList(result);
                },
                (error) => {
                    console.log("Error : ", error);
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }

    useEffect(() => {
        if (isInitialMount.current)
            isInitialMount.current = false;
        else
            refreshComments();
    }, [])

    const checkLikes = () => {
        var likeControl = likes.find(like => (""+like.userId === localStorage.getItem("currentUser")));
        if (likeControl != null) {
            setLikedId(likeControl.id);
            setIsLiked(true);
        }
    }

    useEffect(() => {
        checkLikes();
    }, [])

    const saveLike = () => {
        fetch("/likes", {
            method: "POST",
            headers: {
                "Authorization": localStorage.getItem("tokenKey"),
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                userId: localStorage.getItem("currentUser"),
                postId: postId,
            }),
        })
            .then((res) => res.json())
            .catch((err) => console.log(err))
    }

    const deleteLike = () => {
        fetch("/likes/" + likeId, {
            method: "DELETE",
            headers: {
                "Authorization": localStorage.getItem("tokenKey"),
                "Content-Type": "application/json",
            },
        })
            .catch((err) => console.log(err))
    }

    return (
        <Card sx={{ width: 800, textAlign: "left", margin: 10 }}>
            <CardHeader
                avatar={
                    <Link style={{ textDecoration: "none", boxShadow: "none", color: "white" }} to={{ pathname: '/users/' + userId }}>
                        <Avatar sx={{ background: 'linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)' }} aria-label="recipe">
                            {userName.charAt(0).toUpperCase()}
                        </Avatar>
                    </Link>
                }
                title={title}
            />
            <CardContent>
                <Typography variant="body2" color="text.secondary">
                    {text}
                </Typography>
            </CardContent>
            <CardActions disableSpacing>
                {disabled ?
                    <IconButton disabled onClick={handleLike} aria-label="add to favorites" >
                        <FavoriteIcon style={isLiked ? { color: "red" } : null} />
                    </IconButton> : <IconButton onClick={handleLike} aria-label="add to favorites" >
                        <FavoriteIcon style={isLiked ? { color: "red" } : null} />
                    </IconButton>
                }
                {likeCount}
                <ExpandMore
                    expand={expanded}
                    onClick={handleExpandClick}
                    aria-expanded={expanded}
                    aria-label="show more"
                >
                    <CommentIcon />
                </ExpandMore>
            </CardActions>
            <Collapse in={expanded} timeout="auto" unmountOnExit>
                <Container fixed>
                    {error ? "Error" : isLoaded ? commentList.map((comment) => (
                        <Comment userId={localStorage.getItem("currentUser")} userName={localStorage.getItem("userName")} text={comment.text}></Comment>
                    )) : "Loading"}
                    {disabled ? ""
                        :
                        <CommentForm userId={localStorage.getItem("currentUser")} userName={localStorage.getItem("userName")} postId={postId}></CommentForm>
                    }
                </Container>
            </Collapse>
        </Card>
    )

}

export default Post;