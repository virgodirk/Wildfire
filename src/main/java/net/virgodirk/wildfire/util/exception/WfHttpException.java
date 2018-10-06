package net.virgodirk.wildfire.util.exception;

/**
 * The Exception for WfHttpRequest
 * 
 * <p>HTTP请求时抛出的异常</p>
 * 
 * @author 李晓勇 on 2018年5月7日 上午19:24:18
 * @version Version 3.0
 */
@SuppressWarnings("unused")
public class WfHttpException extends Exception {

    private static final long serialVersionUID = 1L;
    
    
    /**
     * 构造 {@link WfHttpException}
     */
    public WfHttpException() {
        super();
    }
    
    /**
     * 构造 {@link WfHttpException}
     * @param friendlyMsg 可展现给用户的友好异常信息
     */
    public WfHttpException(final String friendlyMsg) {
        super(friendlyMsg, new Throwable(friendlyMsg));
    }
    
    /**
     * 构造 {@link WfHttpException}
     * @param friendlyMsg 可展现给用户的友好异常信息
     * @param cause 异常原因 {@link Throwable}
     */
    public WfHttpException(final String friendlyMsg, final Throwable cause) {
        super(friendlyMsg, cause);
    }
    
    /**
     * 构造 {@link WfHttpException}
     * @param cause 异常原因 {@link Throwable}
     */
    public WfHttpException(final Throwable cause) {
        super(cause.getMessage(), cause);
    }
    
    
    /**
     * 获取友好异常信息
     * @return 可展现给用户的友好异常信息
     */
    public String getFriendlyMessage() {
        return this.getMessage();
    }
    
    /**
     * 获取异常原因详细信息
     * @return 异常原因详细信息，为空时将返回友好异常信息
     *         <p>此信息应尽量禁止展现给用户</p>
     */
    public String getCauseMessage() {
        final Throwable cause = this.getCause();
        if (cause == null) {
            return this.getMessage();
        }
        return cause.getMessage();
    }
}