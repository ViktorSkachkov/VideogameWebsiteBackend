import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Navigation from "./Navigation";
import Home from "./Home";
import Games from "./Games";
import Shop from "./Shop";
import News from "./News";
import Support from "./Support";
import Account from "./Account";
import Footer from "./Footer";
import {useState} from "react";
import ViewGame from "./ViewGame";
import ViewNewsArticle from "./ViewNewsArticle";
import Register from "./Register";
import LogIn from "./LogIn";
import ViewAddition from "./ViewAddition";
import UpdateVideogame from "./UpdateVideogame";
import UpdateAddition from "./UpdateAddition";
import UpdateNewsArticle from "./UpdateNewsArticle";
import AddVideogame from "./AddVideogame";
import AddAddition from "./AddAddition";
import AddNewsArticle from "./AddNewsArticle";

function App() {
    const [loggedUser, setLoggedUser] = useState(null);
return (
    <>
    <Router>
        <Navigation loggedUser={loggedUser} />
        <Routes>
            <Route path="/" element={<Home loggedUser={loggedUser} />}/>
            <Route path="/games" element={<Games loggedUser={loggedUser} />}/>
            <Route path="/shop" element={<Shop loggedUser={loggedUser} />}/>
            <Route path="/news" element={<News loggedUser={loggedUser} />}/>
            <Route path="/support" element={<Support loggedUser={loggedUser} />}/>
            <Route path="/register" element={<Register />}/>
            <Route path="/logIn" element={<LogIn />}/>
            <Route path="/account/:id" element={<Account loggedUser={loggedUser} />}/>
            <Route path="/game/:id" element={<ViewGame loggedUser={loggedUser} />}/>
            <Route path="/addition/:id" element={<ViewAddition loggedUser={loggedUser} />}/>
            <Route path="/newsArticle/:id" element={<ViewNewsArticle loggedUser={loggedUser} />}/>
            <Route path="/updateVideogame/:id" element={<UpdateVideogame loggedUser={loggedUser} />}/>
            <Route path="/updateAddition/:id" element={<UpdateAddition loggedUser={loggedUser} />}/>
            <Route path="/updateNewsArticle/:id" element={<UpdateNewsArticle loggedUser={loggedUser} />}/>
            <Route path="/addVideogame" element={<AddVideogame loggedUser={loggedUser} />}/>
            <Route path="/addAddition" element={<AddAddition loggedUser={loggedUser} />}/>
            <Route path="/addNewsArticle" element={<AddNewsArticle loggedUser={loggedUser} />}/>
        </Routes>
    </Router>
    <Footer/>
    </>
)
}
export default App;