import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { PlayerListPage } from './features/player/pages/PlayerListPage';

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<PlayerListPage />}/>
            </Routes>
        </BrowserRouter>
    </React.StrictMode>
)