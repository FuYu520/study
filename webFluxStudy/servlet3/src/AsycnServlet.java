
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异步servlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AsycnServlet" })
public class AsycnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AsycnServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long t1 = System.currentTimeMillis();
		//开启异步
		AsyncContext startAsync = request.startAsync();
		
		// 执行业务代码
		//把业务代码放入线程池，这里用jdk8 CompletableFuture.执行异步操作
		CompletableFuture.runAsync(() -> doSomeThing(startAsync, startAsync.getRequest(), startAsync.getResponse()));
		
		

		System.out.println("sync use: " + (System.currentTimeMillis() - t1));
	}

	private void doSomeThing(AsyncContext startAsync, ServletRequest request, ServletResponse response) {
		// 模拟一下耗时操作
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 响应
		try {
			response.getWriter().append("done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//业务代码处理完毕，通知结束
		startAsync.complete();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
