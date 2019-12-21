import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {TokenStorageService} from '../auth/token-storage.service';

@Injectable()
export class LoginService {
  private signUpUrl = 'http://localhost:8081/user/register';
  constructor(private http: HttpClient,
              private tokenStorage: TokenStorageService) { }

  login(loginPayload) {
    const headers = {
      Authorization: 'Basic ' + btoa('client-id:password'),
      'Content-type': 'application/x-www-form-urlencoded'
    };
    return this.http.post('http://localhost:8081/' + 'oauth/token', loginPayload, {headers});
  }

  signUp(user: {login: string, password: string}) {
    const headers = {
      'Content-type': 'application/json'
    };
    return this.http.post(this.signUpUrl, user, {headers});
  }

  logout() {
    this.tokenStorage.signOut();
  }
}
