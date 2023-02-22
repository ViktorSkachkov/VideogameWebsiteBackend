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

function App() {
    const [loggedUser, setLoggedUser] = useState(null);
return (
    <>
    <Router>
        <Navigation/>
        <Routes>
            <Route path="/" element={<Home loggedUser={loggedUser} />}/>
            <Route path="/games" element={<Games />}/>
            <Route path="/shop" element={<Shop />}/>
            <Route path="/news" element={<News />}/>
            <Route path="/support" element={<Support />}/>
            <Route path="/register" element={<Register />}/>
            <Route path="/logIn" element={<LogIn />}/>
            <Route path="/account/:id" element={<Account />}/>
            <Route path="/game/:id" element={<ViewGame />}/>
            <Route path="/newsArticle/:id" element={<ViewNewsArticle />}/>
        </Routes>
    </Router>
    <Footer/>
    </>
)
}
export default App;