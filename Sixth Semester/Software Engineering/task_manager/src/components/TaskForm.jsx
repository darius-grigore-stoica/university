import React, { useState } from 'react';

const TaskForm = ({ onSubmit, onCancel }) => {
  const [taskName, setTaskName] = useState('');
  const [taskDescription, setTaskDescription] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({
      name: taskName,
      description: taskDescription,
    });
  };

  return (
    <div className="task-form-overlay">
      <div className="task-form">
        <h3>Assign New Task</h3>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Task Name:</label>
            <input
              type="text"
              value={taskName}
              onChange={(e) => setTaskName(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label>Description:</label>
            <textarea
              value={taskDescription}
              onChange={(e) => setTaskDescription(e.target.value)}
              required
            />
          </div>
          <div className="form-actions">
            <button type="button" onClick={onCancel}>
              Cancel
            </button>
            <button type="submit">Assign Task</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default TaskForm;