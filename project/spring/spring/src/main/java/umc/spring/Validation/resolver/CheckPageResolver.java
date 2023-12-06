package umc.spring.Validation.resolver;

// QueryParamResolver.java
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.Validation.CheckPage;
import umc.spring.exception.handler.PageHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Component
public class CheckPageResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class) && parameter.hasParameterAnnotation(CheckPage.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String paramValue = request.getParameter(parameter.getParameterName());
        try {
            Integer page = Integer.parseInt(paramValue);
            if (!isValid(page)) {
                throw new PageHandler(ErrorStatus.PAGE_NOT_VALID);
            }
            return page-1;
        } catch (NumberFormatException e) {
            throw new PageHandler(ErrorStatus.PAGE_NOT_VALID);
        }
    }

    private boolean isValid(Integer page) {
        return page != null && page > 0;
    }
}

