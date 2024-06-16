import React, { useState } from 'react';
import './App.css';

function App() {
    const [filename, setFilename] = useState(''); // State to hold the filename
    const [loading, setLoading] = useState(false);
    const [approvalResponse, setApprovalResponse] = useState('');

    const handleFilenameChange = (event) => {
        setFilename(event.target.value);
    };

    const handleApprove = () => {
        if (filename.trim() !== '') { // Check if filename is not empty
            if (window.confirm('Are you sure you want to approve this file?')) {
                console.log('Approving file:', filename);
                setLoading(true);

                fetch('http://10.241.109.82:8069/first-approval', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ filename: filename.trim() }) // Send filename in JSON format
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(res => {
                    console.log('Approval response:', res);
                    setApprovalResponse(res.message);
                    setLoading(false);
                })
                .catch(error => {
                    console.error('Error approving:', error);
                    setLoading(false);
                });
            }
        } else {
            console.warn('Filename is empty');
        }
    };

    return (
        <div className="container">
            <div className="content">
                <h2>Please review your file carefully before approval</h2>
                <p>Ensure that the contents of the file are correct before clicking the approve button. This action will finalize the file for database upload.</p>
                <h3>Include the filename received in your email, with the extension and any numbers after it.</h3>
                <p>Example: <code>Example.csv.123456789</code></p>
                <input
                    type="text"
                    placeholder="Enter filename"
                    value={filename}
                    onChange={handleFilenameChange}
                    className="input-field"
                />
                <br />
                <button onClick={handleApprove} disabled={loading} className="approve-button">
                    {loading ? 'Approving...' : 'Approve'}
                </button>
                {loading && <div className="spinner"></div>}
            </div>
            {approvalResponse && (
                <div className="response">
                    <h3>Approval Response:</h3>
                    <p className="success-message">{approvalResponse}</p>
                </div>
            )}
        </div>
    );
}

export default App;
