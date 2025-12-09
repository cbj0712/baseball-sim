import { ClubSummaryPanel } from '../features/player/components/ClubSummaryPanel/ClubSummaryPanel';
import { PlayerListPage } from '../features/player/pages/PlayerListPage'
import { AppLayout } from './layout/AppLayout';

function App() {
    return (
        <AppLayout>
            <PlayerListPage />
            <ClubSummaryPanel />
        </AppLayout>
    );
}

export default App
