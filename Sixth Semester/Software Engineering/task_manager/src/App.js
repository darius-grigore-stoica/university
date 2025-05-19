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

  const handleLogout = async () => {
    if (user) {
      try {
      const endpoint = user.role === 'boss' ? 'boss' : 'employees';

      await fetch(`http://localhost:8080/api/${endpoint}/logout`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username: user.username }),
      });
      } catch (error) {
        console.error('Logout error:', error);
      }
  }

  setUser(null);
  setIsBoss(false);
  };

  return (
    <div className="app">
      {user && <Navbar onLogout={handleLogout} isBoss={isBoss} />}
      {!user ? (
        <Login onLogin={handleLogin} />
      ) : isBoss ? (
        <BossDashboard  bossId={user.id} username={user.username} />
      ) : (
        <EmployeeDashboard employeeId={user.id} username={user.username}/>
      )}
    </div>
  );
}

export default App;