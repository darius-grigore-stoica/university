import React, { useState } from 'react';

const Login = ({ onLogin }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    const credentials = {
      username,
      password,
    };

    try {
      const [employeeRes, bossRes] = await Promise.allSettled([
        fetch('http://localhost:8080/api/employees/login', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(credentials),
        }),
        fetch('http://localhost:8080/api/boss/login', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(credentials),
        }),
      ]);

      if (employeeRes.status === 'fulfilled' && employeeRes.value.ok) {
        const data = await employeeRes.value.json();
        onLogin(data);
      } else if (bossRes.status === 'fulfilled' && bossRes.value.ok) {
        const data = await bossRes.value.json();
        onLogin(data);
      } else {
        setError('Invalid credentials.');
      }
    } catch (err) {
      console.error(err);
      setError('An error occurred while logging in.');
    }
  };

  return (
    <div className="login-container">
      <h2>Company Task System</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {error && <div className="error-message">{error}</div>}
        <button type="submit" className="login-button">
          Login
        </button>
      </form>
    </div>
  );
};

export default Login;
