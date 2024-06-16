import React, { useState } from 'react';
import './App.css';

function App() {
    const [selectedFile, setSelectedFile] = useState(null);
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(false);
    const [rowLoading, setRowLoading] = useState([]);
    const [uploadMessage, setUploadMessage] = useState('');

    const handleFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const handleUpload = () => {
        if (selectedFile) {
            if (window.confirm('Are you sure you want to upload this file?')) {
                console.log('Uploading file:', selectedFile);
                setLoading(true);
                setUploadMessage('');

                const formData = new FormData();
                formData.append('csv', selectedFile);
                fetch('http://10.241.109.82:8069/upload', {
                    method: 'POST',
                    body: formData
                })
                .then(response => response.json())
                .then(res => {
                    console.log('Uploaded', res);
                    const parsedData = JSON.parse(res.output); // Parse the JSON string
                    setData(parsedData);
                    setLoading(false);
                    setRowLoading(parsedData.map(() => true));
                    setUploadMessage('File uploaded and data processed successfully!');

                    // Simulate loading effect
                    setTimeout(() => {
                        setRowLoading(parsedData.map(() => false));
                    }, 1000); // Adjust timing as needed
                })
                .catch(error => {
                    console.error('Error: ', error);
                    setLoading(false);
                    setUploadMessage('Failed to upload the file.');
                });
            }
        } else {
            console.warn('No file selected');
            setUploadMessage('Please select a file to upload.');
        }
    };

    return (
        <div className="container">
            <div className="content">
                <h2>File Upload</h2>
                <input type="file" onChange={handleFileChange} className="input-file"/>
                <button onClick={handleUpload} className="upload-button">Upload</button>
                {loading && <div className="spinner"></div>}
                {uploadMessage && (
                    <div className={`message ${uploadMessage.includes('successfully') ? 'success' : 'error'}`}>
                        {uploadMessage}
                    </div>
                )}
            </div>
            {data.length > 0 && (
                <table>
                    <thead>
                        <tr>
                            {Object.keys(data[0]).map((key) => (
                                <th key={key}>{key}</th>
                            ))}
                        </tr>
                    </thead>
                    <tbody>
                        {data.map((item, index) => (
                            <tr key={index} className={rowLoading[index] ? 'loading' : 'loaded'}>
                                {Object.values(item).map((value, idx) => (
                                    <td key={idx}>{value}</td>
                                ))}
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default App;
