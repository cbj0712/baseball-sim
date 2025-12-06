import { ReactNode } from 'react';
import { AppHeader } from './AppHeader';
import styles from './AppLayout.module.scss';

interface AppLayoutProps {
    children: ReactNode;
}

export function AppLayout({ children }: AppLayoutProps) {
    return (
        <div className={styles.root}>
            <AppHeader />
            <main className={styles.main}> {children} </main>
        </div>
    )
}