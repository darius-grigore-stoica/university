import React from 'react';

const Navbar = ({ onLogout, isBoss }) => {
  return (
    <nav className="navbar">
      <div className="navbar-brand">Task Management System</div>
      <div className="navbar-user">
        <span>Logged in as {isBoss ? 'Boss' : 'Employee'}</span>
        <button onClick={onLogout} className="logout-button">
          Logout
        </button>
      </div>
    </nav>
  );
};

export default Navbar;