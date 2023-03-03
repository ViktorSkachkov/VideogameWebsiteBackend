import VideogameCard from "./VideogameCard";
import {useState, useEffect} from "react";
import NewsArticleCard from "./NewsArticleCard";
import '../css/News.css';
import axios from "axios";
import VideogameCardAdmin from "./VideogameCardAdmin";
import NewsArticleCardAdmin from "./NewsArticleCardAdmin";
import {useNavigate} from "react-router-dom";

const News = (loggedUser) => {
    const [newsArticles, setNewsArticles] = useState([]);
    let navigate = useNavigate();

    useEffect(() => {
        getVideogames();
    }, []);

    const getVideogames = () => {
        var config = {
            method: "get",
            url: `http://localhost:8080/news`,
            /*headers: {
                "Authorization": `Bearer ${token}`,
            },*/
        };
        axios(config)
            .then(function (response) {
                setNewsArticles(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    return (
        <>
            <center>
                <h1 className="title">NEWS</h1>
                <h3 className="title">Filters</h3>
                {loggedUser.loggedUser != null ?
            <div className="listOfNewsArticles">
                {newsArticles.map((newsArticle) => (
                    <NewsArticleCard newsArticle={newsArticle} />
                ))}
            </div> : <> <div className="listOfNewsArticles">
                            {newsArticles.map((newsArticle) => (
                                <NewsArticleCardAdmin newsArticle={newsArticle} />
                            ))}
                        </div>
                        <button onClick={() => {
                            navigate(`/addNewsArticle`, {
                            });
                        }}>Add News Article</button>
                    </>
                }
            </center><br/>
        </>
    )
}
export default News;