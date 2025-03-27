import React, { useState } from 'react';
import Login from './components/Login';
import EmployeeDashboard from './components/EmployeeDashboard';
import BossDashboard from './components/BossDashboard';
import Navbar from './components/Navbar';
import './App.css';

function App() {
  const [user, setUser] = useState(null);
  const [isBoss, setIsBoss] = useState(false);

  const handleLogin = (userData) => {
    setUser(userData);
    setIsBoss(userData.role === 'boss');
  };

  const handleLogout = () => {
    setUser(null);
    setIsBoss(false);
  };

  return (
    <div className="app">
      {user && <Navbar onLogout={handleLogout} isBoss={isBoss} />}
      {!user ? (
        <Login onLogin={handleLogin} />
      ) : isBoss ? (
        <BossDashboard />
      ) : (
        <EmployeeDashboard />
      )}
    </div>
  );
}

export default App;