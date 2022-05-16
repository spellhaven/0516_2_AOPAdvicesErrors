package com.spellhaven.spring0516_2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


@Aspect // @는 이 클래스가 Aspect 클래스라는 걸 알려 준다.
public class LogAop {
	
	
//	@Pointcut("execution(public void get*(..))") //public void인 모든 get메서드
//	@Pointcut("execution(* com.javagyojin.ex.*.*())") //com.javagyojin.ex 패키지에 파라미터가 없는 모든 메서드	
//	@Pointcut("execution(* com.javagyojin.ex..*.*())") //com.javagyojin.ex 패키지 & com.javagyojin.ex 하위 패키지에 파라미터가 없는 모든 메서드 
//	@Pointcut("execution(* com.javagyojin.ex.Worker.*())") //com.javagyojin.ex.Worker 클래스안에 있는 모든 메서드
		
//	@Pointcut("within(com.javagyojin.ex.*)") //com.javagyojin.ex 패키지 안에 있는 모든 메서드
//	@Pointcut("within(com.javagyojin.ex..*)") //com.javagyojin.ex 패키지 & 하위 패키지 안에 있는 모든 메서드
//	@Pointcut("within(com.javagyojin.ex.Worker)") //com.javagyojin.ex.Worker 클래스 안에 있는 모든 메서드
	
//	@Pointcut("bean(student)") //student 빈에만 적용
//	@Pointcut("bean(*ker)") //*ker로 끝나는 이름의 빈에만 적용. (이 프로젝트에선 stu1은 놓치고 worker1 Bean은 잡히겠지?)
	
	@Pointcut("bean(*1)") //"1로 끝나는 Bean만 잡아서 공통 기능 실행시켜라". 이 프로젝트에선 stu1, worker1 둘 다 잡히겠다.
	private void pointcutMethod() {
		
	}
	
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
					
		// 공통 기능이 적용될 메소드(joinpoint)의 이름 불러오기
		String signatureStr = joinpoint.getSignature().toShortString();
		
		// joinpoint 메소드의 이름 출력, ㅋ
		System.out.println(signatureStr + "메소드가 시작되었습니다, ㅋ");	
		
		long st = System.currentTimeMillis(); // start time으로 현재 시간 가져오기(밀리초 단위)해 봤다, ㅋ.
		
		
		try {
			Object obj = joinpoint.proceed();  // joinpoint 메소드 ㄱㄱ하는 놈.
			return obj;
		} finally {
			long et = System.currentTimeMillis(); // 느그는 end time이야.
			System.out.println(signatureStr + "메소드가 종료되었디!");
			System.out.println(signatureStr + "경과 시간: " + (et - st)); // et - st 하면 이 메소드가 얼마나 실행됐는지 걸린 시간이 나옴.
		}
	
	}

	@Before("pointcutMethod()")
	public void beforeAdvice() {
		System.out.println("beforeAdvice가 실행됨!");
	}
	
	@AfterReturning("pointcutMethod()")
	public void afterReturningAdvice() {
		System.out.println("afterReturningAdvice가 실행됨!");
	}
	
	@AfterThrowing("pointcutMethod()")
	public void afterThrowingAdvice() {
		System.out.println("afterThrowingAdvice가 실행됨!");
	}
	
	@After("pointcutMethod()")
	public void afterAdvice() {
		System.out.println("afterAdvice가 실행됨!");
	}
}
