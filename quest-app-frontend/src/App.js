import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar/Navbar';
import Home from './components/Home/Home';
import User from './components/User/User';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route exact path = "/" Component={Home} />
          <Route exact path = "/users/:userId" Component={User} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
