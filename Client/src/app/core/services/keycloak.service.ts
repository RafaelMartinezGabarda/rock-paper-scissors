import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';
import { environment } from '../../../environments/environment';

export interface UserProfile {
  sub: string;
  email: string;
  given_name: string;
  family_name: string;
  token: string;
}

@Injectable({
  providedIn: 'root',
})
export class KeycloakService {
  _keycloak: Keycloak | undefined;
  profile: UserProfile | undefined;

  get keycloak() {
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: environment.authConfig.idpUrl,
        realm: environment.authConfig.realm,
        clientId: environment.authConfig.clientId,
      });
    }
    return this._keycloak;
  }

  async init() {
    const authenticated = await this.keycloak.init({
      onLoad: 'check-sso',
      silentCheckSsoRedirectUri:
        window.location.origin + '/silent-check-sso.html',
    });

    if (!authenticated) {
      return authenticated;
    }
    this.profile =
      (await this.keycloak.loadUserInfo()) as unknown as UserProfile;
    this.profile.token = this.keycloak.token || '';

    return true;
  }

  login() {
    return this.keycloak.login();
  }

  logout() {
    return this.keycloak.logout({
      redirectUri: window.location.origin,
    });
  }
}
