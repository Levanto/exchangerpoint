package com.exchangerpoint.walletservices.converter;

import com.exchangerpoint.commonservices.errorcode.RestErrorCode;
import com.exchangerpoint.commonservices.exception.RestException;
import com.exchangerpoint.databaseservices.entity.UserWallet;
import com.exchangerpoint.restspec.dto.wallet.WalletResponse;
import com.exchangerpoint.restspec.dto.wallet.WalletsResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public class WalletResponseConverter {

	public static WalletResponse WalletResponse(UserWallet userWallet) throws RestException {

		WalletResponse WalletResponse = new WalletResponse();
		try {
			if (userWallet != null)
				BeanUtils.copyProperties(WalletResponse, userWallet);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),
					RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());
		}
		return WalletResponse;
	}

	public static WalletsResponse WalletsResponse(List<UserWallet> UserWalletList) throws RestException {

		WalletsResponse WalletsResponse = new WalletsResponse();
		if (UserWalletList != null) {
			for (UserWallet userWallet : UserWalletList) {
				WalletResponse WalletResponse = new WalletResponse();
				try {
					BeanUtils.copyProperties(WalletResponse, userWallet);
					WalletsResponse.addWalletsItem(WalletResponse);
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RestException(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),
							RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());
				}

			}
		}
		return WalletsResponse;
	}

}
