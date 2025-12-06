import { httpClient } from '@/shared/lib/httpClient';
import type { PlayerDetailResponse, PlayerSummaryResponse } from '../types/player';

interface ApiResponse<T> {
    success: boolean;
    code: string;
    message: string;
    data: T;
}

export async function fetchPlayers(): Promise<PlayerSummaryResponse[]> {
    const response = await httpClient.get<ApiResponse<PlayerSummaryResponse[]>>('/api/players');

    const body = response.data;

    if (!body.success) {
        throw new Error(body.message || body.code || 'Failed to fetch players');
    }

    return body.data ?? [];
}

export async function fetchPlayerDetail(id: number): Promise<PlayerDetailResponse> {
    const response = await httpClient.get<ApiResponse<PlayerDetailResponse>>(`/api/players/${id}`);

    return response.data.data;
}