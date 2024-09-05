import {
  computed,
  inject,
  Injectable,
  OnInit,
  PLATFORM_ID,
  signal,
} from '@angular/core';
import { KeycloakService } from './keycloak.service';
import { isPlatformServer } from '@angular/common';
import { BehaviorSubject, Subject } from 'rxjs';

export interface User {
  id: string;
  email: string;
  name: string;
  anonymous: boolean;
  bearer: string;
}

export const ANONYMOUS_USER: User = {
  id: '',
  email: 'nomail',
  name: 'no user',
  anonymous: true,
  bearer: '',
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  keycloakService = inject(KeycloakService);

  loaded = new BehaviorSubject<boolean>(false);
  user = new BehaviorSubject<User | undefined>(undefined);

  constructor() {
    this.onInit();
  }

  onInit() {
    const isServer = isPlatformServer(inject(PLATFORM_ID));
    const keycloakService = inject(KeycloakService);
    if (isServer) {
      this.user.next(ANONYMOUS_USER);
      this.loaded.next(true);
      return;
    }

    keycloakService.init().then((isLoggedIn) => {
      if (isLoggedIn && keycloakService.profile) {
        const { sub, email, given_name, family_name, token } =
          keycloakService.profile;
        const user = {
          id: sub,
          email,
          name: `${given_name} ${family_name}`,
          anonymous: false,
          bearer: token,
        };
        this.user.next(user);
        this.loaded.next(true);
      } else {
        this.user.next(ANONYMOUS_USER);
        this.loaded.next(true);
      }
    });
  }

  async signIn() {
    try {
      await this.keycloakService.login();
    } catch (err) {
      alert('Error trying to contact keycloak');
    }
  }

  async signOut() {
    try {
      await this.keycloakService.logout();
    } catch (err) {
      alert('Error trying to contact keycloak');
    }
  }
}
