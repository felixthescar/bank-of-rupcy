import { useState, useEffect } from 'react';
import reactLogo from './assets/react.svg';
import viteLogo from '/vite.svg';
import './App.css';

function App() {
  const [selectedFile, setSelectedFile] = useState(null);
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);
  const [rowLoading, setRowLoading] = useState([]);

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const handleUpload = () => {
    if (selectedFile) {
      console.log('upload file:', selectedFile);
      setLoading(true);

      const formData = new FormData();
      formData.append('csv', selectedFile);
      fetch('http://172.17.0.1:8069/upload', {
        method: 'POST',
        body: formData
      })
      .then(response => response.json())
      .then(res => {
        console.log('uploaded', res);
        setData(res);
        setLoading(false);
        setRowLoading(res.map(() => true));

        // Simulate loading effect
        setTimeout(() => {
          setRowLoading(res.map(() => false));
        }, 1000); // Adjust timing as needed
      })
      .catch(error => {
        console.error('error: ', error);
        setLoading(false);
      });
    } else {
      console.warn('no file selected');
    }
  };

  return (
    <div>
      <div>
        <h2>File Upload</h2>
        <input type="file" onChange={handleFileChange} />
        <button onClick={handleUpload}>Upload</button>
      </div>
      {loading && <div className="spinner"></div>}
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
