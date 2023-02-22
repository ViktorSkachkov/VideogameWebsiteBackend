import {useNavigate} from "react-router-dom";
const NewsArticleCart = (newsArticle) => {
    let navigate = useNavigate();

    return (
        <div className="newsArticleCart"  onClick={() => {
            navigate(`/newsArticle/${newsArticle.newsArticle.id}`, {
            });
        }}>
            <div>
                <img src="69piR5.jpg" height="400px" width="450px" alt=""/>
            </div>
        </div>
    )
}
export default NewsArticleCart;