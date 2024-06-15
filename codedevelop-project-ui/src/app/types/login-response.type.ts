import { User } from "../models/user"

export type LoginResponse = {
    type: string;
    token: string;
}