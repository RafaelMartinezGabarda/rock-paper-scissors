import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const keycloakService = inject(AuthService);

  const bearer = keycloakService.user.getValue()?.bearer;

  if (!bearer) {
    return next(req);
  }

  return next(
    req.clone({
      headers: req.headers.set('Authorization', `Bearer ${bearer}`),
    })
  );
};
