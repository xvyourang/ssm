package cn.xyr.ssm;//package jinsheng;
//
//
//import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
//import com.gargoylesoftware.htmlunit.Page;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.WebResponse;
//import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
//import com.gargoylesoftware.htmlunit.html.HtmlForm;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
//import com.gargoylesoftware.htmlunit.javascript.host.event.Event;
//
//import javax.swing.*;
//import java.util.List;
//
//public class helloHtmlUnit {
//    public static HtmlPage resultPage;
//    public static HtmlPage page;
//    public static boolean working = false;
//    public final static String url = "";
//    static WebClient webClient;
//
//    /**
//     * 连接获取注册页面
//     *
//     * @throws FailingHttpStatusCodeException
//     * @throws MalformedURLException
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    public static void open() throws Exception {
//        //创建一个webclient
//        webClient = new WebClient();
//        //htmlunit 对css和javascript的支持不好，所以请关闭之
//        webClient.getOptions().setJavaScriptEnabled(true);
//        webClient.getOptions().setCssEnabled(true);
//        webClient.getOptions().setRedirectEnabled(true);// 3 启动客户端重定向
//        webClient.getOptions().setThrowExceptionOnScriptError(false);
////     webClient.getOptions().setTimeout(50000);
//        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//        webClient.getOptions().setThrowExceptionOnScriptError(false);
//        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
//        //获取注册页面(
//        page = webClient.getPage(url);
//        webClient.waitForBackgroundJavaScript(30000);
//    }
//
//    /**
//     * 显示信息
//     *
//     * @param str 信息
//     */
//    public static void showMessage(final String str) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                JOptionPane.showMessageDialog(null, str);
//            }
//        });
//    }
//
//    /**
//     * 判断邮箱是否已注册
//     */
//
//    public static void isRegister() throws Exception {
//        if (page == null) {
//            open();
//            Thread.sleep(3000L);
//        }
//        HtmlForm form = page.getFormByName("UnifiedSysUserDetailDataForm");
////    获取提交按钮
//        HtmlButtonInput button = form.getInputByName("liteRegistrationButton");
//        HtmlTextInput UserContact_EmailAddress =  form.getInputByName("UserContact_EmailAddress"); //电子邮箱
//        UserContact_EmailAddress.type("214@qq.com");
//        Event event = new Event();
//        //获取input上邮箱上的onblur事件，依次类推其他事件；
//        event.setEventType(Event.TYPE_BLUR);
//        HtmlPage hpm = UserContact_EmailAddress.click(event);
////     Thread.sleep(3000L);
//        String tx = hpm.asText();
//        webClient.close();
//        if (tx.contains("该邮箱地址已注册， 登录.")) {
//            System.out.println("该邮箱地址已注册， 登录.");
//        } else {
//            submittingForm();
//        }
//    }
//
//    /**
//     * 自动注册（提交表单）
//     *
//     * @throws Exception
//     */
//    public static void submittingForm() throws Exception {
//        String str2;
//        if (page == null) {
//            open();
//        }
////   HtmlAnchor link=pageAll.getAnchorByHref("https://www.thermofisher.com/order/catalog/zh/CN/adirect/lt?cmd=partnerMktLogin");
////   HtmlPage pageLogin= link.click();
////   Thread.sleep(1000L);
//        //    获取表单
////   HtmlAnchor linkRegister=pageLogin.getAnchorByHref(urlRegister);
////   HtmlPage page= linkRegister.click();
//        HtmlForm form = page.getFormByName("UnifiedSysUserDetailDataForm");
//        List<HtmlForm> forms = page.getForms();
//        HtmlForm form2 = forms.get(2);
//        System.out.println("停顿.." + form2);
////    获取提交按钮
//        HtmlButtonInput button = form.getInputByName("liteRegistrationButton");
////    获取表单项
//        HtmlTextInput UserContact_LastName = (HtmlTextInput) form.getInputByName("UserContact_LastName"); //姓
//        HtmlTextInput UserContact_FirstName = (HtmlTextInput) form.getInputByName("UserContact_FirstName"); //名
//
////    填充表单
//        UserContact_LastName.click();
//        UserContact_LastName.type("张");
////     UserContact_LastName.setValueAttribute("三");
////    ……
//        Page page3 = page.executeJavaScript("javascript:getValidateEmailAddressInfo();").getNewPage();
//        Page page5 = page.executeJavaScript("javascript:getEmailAddressDetail('formObject');").getNewPage();
//        for (int i = 0; i < 20; i++) {
//            if (page3 != null) {
//                System.out.println("等待ajax执行完毕1");
//                break;
//            }
//            synchronized (resultPage) {
//                page.wait(500);
//            }
//        }
//        for (int i = 0; i < 20; i++) {
//            if (page5 != null) {
//                System.out.println("等待ajax执行完毕2");
//                break;
//            }
//            synchronized (resultPage) {
//                page.wait(500);
//            }
//        }
////
//        htmlSelet.getOption(44).setSelected(true);
//        UserContact_UserSecretQuestionCode.getOption(1).setSelected(true);
//        SecretAnswer.click();
//        SecretAnswer.type("dog");
//        SecretAnswer.setValueAttribute("dog");
//        radioButtonCts.get(0).setChecked(false);
//        radioButtonCts.get(1).setChecked(true);
//
////    提交表单
//
//
//        resultPage =  button.click();
//        Thread.sleep(3000L);
//        WebResponse res = resultPage.getWebResponse();
//        String html = res.getContentAsString(); //
////     String pageContent = page2.getWebResponse().getContentAsString();
////     //获取页面的TITLE
////     str1 = resultPage.getHead().asXml();
//        //获取页面的XML代码
////     str2 = resultPage.asXml();
////     //获取页面的文本
//        str2 = resultPage.asText();
//        System.out.println(str2);
//        //关闭webclient Closes all opened windows, stopping all background JavaScript processing.
//        webClient.close();
//    }
//
//
//    public static void main(String[] args) {
//        try {
//            isRegister();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}