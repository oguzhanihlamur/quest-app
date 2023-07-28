import './App.css';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './components/Navbar/Navbar';
import Home from './components/Home/Home';
import User from './components/User/User';
import Auth from './components/Auth/Auth';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route exact path="/" Component={Home} />
          <Route exact path="/users/:userId" Component={User} />
          <Route exact path="/auth" element={localStorage.getItem("currentUser") != null ? <Navigate to="/" /> : <Auth />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
