
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
 * �첽servlet
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
		//�����첽
		AsyncContext startAsync = request.startAsync();
		
		// ִ��ҵ�����
		//��ҵ���������̳߳أ�������jdk8 CompletableFuture.ִ���첽����
		CompletableFuture.runAsync(() -> doSomeThing(startAsync, startAsync.getRequest(), startAsync.getResponse()));
		
		

		System.out.println("sync use: " + (System.currentTimeMillis() - t1));
	}

	private void doSomeThing(AsyncContext startAsync, ServletRequest request, ServletResponse response) {
		// ģ��һ�º�ʱ����
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ��Ӧ
		try {
			response.getWriter().append("done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//ҵ����봦����ϣ�֪ͨ����
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
