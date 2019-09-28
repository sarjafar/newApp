package uz.parahat.newsApp.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	EDITOR, ADMIN;

	@Override
	public String getAuthority() {
		return name();
	}
}
