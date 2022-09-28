package com.cts.digitalbook.digitalbookreaderservice.services;

import com.cts.digitalbook.digitalbookreaderservice.entities.SubscribedBookDetails;
import com.cts.digitalbook.digitalbookreaderservice.entities.SubscriptionEntity;

public interface EntityDtoMapper {

	SubscribedBookDetails subscriptionBookDetails(SubscriptionEntity subscriptionEntity);

}
