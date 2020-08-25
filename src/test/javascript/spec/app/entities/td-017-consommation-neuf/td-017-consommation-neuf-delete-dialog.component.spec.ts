import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SuivEnergiesTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { TD017ConsommationNeufDeleteDialogComponent } from 'app/entities/td-017-consommation-neuf/td-017-consommation-neuf-delete-dialog.component';
import { TD017ConsommationNeufService } from 'app/entities/td-017-consommation-neuf/td-017-consommation-neuf.service';

describe('Component Tests', () => {
  describe('TD017ConsommationNeuf Management Delete Component', () => {
    let comp: TD017ConsommationNeufDeleteDialogComponent;
    let fixture: ComponentFixture<TD017ConsommationNeufDeleteDialogComponent>;
    let service: TD017ConsommationNeufService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD017ConsommationNeufDeleteDialogComponent],
      })
        .overrideTemplate(TD017ConsommationNeufDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD017ConsommationNeufDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD017ConsommationNeufService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
