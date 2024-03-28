// Spring security를 사용하려면 AbstractSecurityWebApplicationInitializer를 상속받는 클래스를 반드시 작성해야 함
// AbstractSecurityWebApplicationInitializer를 상속받는 클래스가 있을 경우 Spring security가 제공하는 필터들을 사용할 수 있도록 활성화 해줌
package com.board.member.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}
