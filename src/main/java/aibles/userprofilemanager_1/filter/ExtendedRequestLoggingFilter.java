package aibles.userprofilemanager_1.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebFilter("/*")
public class ExtendedRequestLoggingFilter implements Filter {

    private PrintWriter logWriter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            logWriter = new PrintWriter(new FileWriter("request_log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Ghi lại thông tin về request vào tệp
        String requestInfo = LocalDateTime.now() + " - " +
                "Request from: " + servletRequest.getRemoteAddr() +
                ", Method: " + servletRequest.getReader() +
                ", URI: " + servletRequest.getLocalName();

        logWriter.println(requestInfo);
        logWriter.flush();


        filterChain.doFilter(servletRequest, servletResponse);


    }

    @Override
    public void destroy() {

        if (logWriter != null) {
            logWriter.close();
        }
    }
}
