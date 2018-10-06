package net.virgodirk.wildfire.util.exception;

/**
 * The Exception for WfFile
 * 
 * <p>进行文件相关操作时抛出的异常</p>
 * 
 * @author 李晓勇 on 2017年8月26日 上午8:55:18
 * @version Version 3.0
 */
@SuppressWarnings("unused")
public class WfFileException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    
    /**
     * 构造 {@link WfFileException}
     */
    public WfFileException() {
        super();
    }

    /**
     * 构造 {@link WfFileException}
     * @param friendlyMsg 可展现给用户的友好异常信息
     */
    public WfFileException(final String friendlyMsg) {
        super(friendlyMsg, new Throwable(friendlyMsg));
    }

    /**
     * 构造 {@link WfFileException}
     * @param friendlyMsg 可展现给用户的友好异常信息
     * @param cause 异常原因 {@link Throwable}
     */
    public WfFileException(final String friendlyMsg, final Throwable cause) {
        super(friendlyMsg, cause);
    }

    /**
     * 构造 {@link WfFileException}
     * @param cause 异常原因 {@link Throwable}
     */
    public WfFileException(final Throwable cause) {
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