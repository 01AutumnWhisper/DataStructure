#include "cc_array.h"
#include <stdlib.h>
#include <string.h>

static inline void cc_array_get_(struct cc_array *self, size_t index, void *result)
{
	memcpy(result, self->array + index * self->elem_size, self->elem_size);
}

static inline void cc_array_get_ref_(struct cc_array *self, size_t index, void **ref)
{
	*ref = self->array + index * self->elem_size;
}

static inline void cc_array_set_(struct cc_array *self, size_t index, void *value)
{
	memcpy(self->array + index * self->elem_size, value, self->elem_size);
}

void cc_array_swap(struct cc_array *self, size_t i, size_t j)
{
	unsigned char tmp[self->elem_size];
	cc_array_get_(self, i, tmp);
	memcpy(self->array + i * self->elem_size, self->array + j * self->elem_size, self->elem_size);
	cc_array_set_(self, j, tmp);
}

int cc_array_cmp(struct cc_array *self, cc_cmp_fn_t cmp, size_t i, size_t j)
{
	return cmp(self->array + i * self->elem_size, self->array + j * self->elem_size);
}

int cc_array_is_valid_index(struct cc_array *self, size_t index)
{
	return index < self->elem_nums;
}

void cc_array_get_unsafe(struct cc_array *self, size_t index, void *result)
{
	cc_array_get_(self, index, result);
}

void cc_array_get_ref_unsafe(struct cc_array *self, size_t index, void **ref)
{
	cc_array_get_ref_(self, index, ref);
}

int cc_array_get(struct cc_array *self, size_t index, void *result)
{
	if (result == NULL)
		return 1;
	if (index >= self->elem_nums)
		return CC_ARRAY_OUT_OF_RANGE;

	cc_array_get_(self, index, result);
	return 0;
}

int cc_array_get_ref(struct cc_array *self, size_t index, void **ref)
{
	if (try_reset_double_p(ref))
		return 1;
	if (index >= self->elem_nums)
		return CC_ARRAY_OUT_OF_RANGE;

	cc_array_get_ref_(self, index, ref);
	return 0;
}

void cc_array_set_unsafe(struct cc_array *self, size_t index, void *value)
{
	cc_array_set_(self, index, value);
}

int cc_array_set(struct cc_array *self, size_t index, void *value)
{
	if (index >= self->elem_nums)
		return CC_ARRAY_OUT_OF_RANGE;

	cc_array_set_(self, index, value);
	return 0;
}

int cc_array_reverse(struct cc_array *self, size_t start, size_t end)
{
	size_t middle, i;

	if (start == end)
		return 1;
	if (start >= self->elem_nums)
		return CC_ARRAY_OUT_OF_RANGE;
	if (end > self->elem_nums)
		end = self->elem_nums;

	middle = (end - start) / 2;
	for (i = 0; i < middle; i++)
		cc_array_swap(self, start + i, end - 1 - i);

	return 0;
}

int cc_array_init(struct cc_array *self, unsigned char *array, size_t elem_nums, size_t elem_size)
{
	self->elem_nums = elem_nums;
	self->elem_size = elem_size;
	self->array = array;
	return 0;
}

#ifndef NO_MALLOC

int cc_array_new(struct cc_array **self, size_t elem_nums, size_t elem_size)
{
	struct cc_array *tmp;
	unsigned char *array;

	tmp = malloc(sizeof(*tmp));
	if (tmp == NULL)
		goto fail1;

	array = malloc(elem_nums * elem_size);
	if (array == NULL)
		goto fail2;

	if (cc_array_init(tmp, array, elem_nums, elem_size))
		goto fail3;

	*self = tmp;
	return 0;

fail3:
	free(array);
fail2:
	free(tmp);
fail1:
	return 1;
}

int cc_array_delete_keep_array(struct cc_array *self)
{
	free(self);
	return 0;
}

int cc_array_delete(struct cc_array *self)
{
	free(self->array);
	free(self);
	return 0;
}

#endif

static struct cc_iter_i iterator_interface = {
	.next = (cc_iter_next_fn_t)cc_array_iter_next,
};

int cc_array_iter_next(struct cc_array_iter *self, void **item, size_t *index)
{
	if (try_reset_double_p(item))
		return 1;
	if (cc_array_get_ref(self->array, self->cursor, item))
		return 2;

	if (index != NULL)
		*index = self->cursor;

	self->cursor++;
	return 0;
}

int cc_array_iter_init(struct cc_array_iter *self, struct cc_array *array)
{
	if (array == NULL)
		return 1;

	self->iterator = &iterator_interface;
	self->array = array;
	self->cursor = 0;
	return 0;
}
